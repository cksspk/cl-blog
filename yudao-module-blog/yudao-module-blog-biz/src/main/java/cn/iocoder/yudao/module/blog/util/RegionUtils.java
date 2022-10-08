package cn.iocoder.yudao.module.blog.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RegionUtils {

    private RegionUtils(){}

    private static byte[] vIndex = null;


    /**
     * 根据Ip使用ip2region进行查询地址
     *
     * @param ip ip地址
     * @return 地址信息
     */
    public static String getCityInfoByIp(String ip) {

        String path = "ip2region/ip2region.xdb";
        File file = new FileReader(path).getFile();
        String dbPath = file.getAbsolutePath();

        if ( vIndex == null ) {
            try {
                vIndex = Searcher.loadVectorIndexFromFile(dbPath);
            } catch (Exception e) {
                log.error("failed to load vector index from `{}`: {}\n", dbPath, e);
                return "";
            }
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (Exception e) {
            log.error("failed to create vectorIndex cached searcher with `{}`: {}\n", dbPath, e);
            return "";
        }

        // 3、查询
        try {

            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            if (log.isDebugEnabled()) {
                log.debug("{region: {}, ioCount: {}, took: %{} μs}\n", region, searcher.getIOCount(), cost);
            }
            String address = StrUtil.replace(region, "0|", "");

            return "内网IP|内网IP".equals(address) ? "内网IP" : address;

        } catch (Exception e) {
            log.error("failed to search({}): {}\n", ip, e);
        }

        // 4、关闭资源
        try {
            searcher.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }


}
