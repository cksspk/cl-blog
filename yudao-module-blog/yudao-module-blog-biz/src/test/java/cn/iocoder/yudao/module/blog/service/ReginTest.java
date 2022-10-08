package cn.iocoder.yudao.module.blog.service;


import cn.iocoder.yudao.module.blog.util.RegionUtils;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class ReginTest {

    @Test
    public void testRegin() throws IOException {
        // 1、创建 searcher 对象
        String dbPath = "D:\\cksspk\\project\\blog\\cl-blog\\yudao-module-blog\\yudao-module-blog-biz\\src\\main\\resources\\ip2region\\ip2region.xdb";
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、查询
        String ip = "81.68.158.8";
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        searcher.close();

        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。


    }

    @Test
    public void test2() {
        String ip = "172.16.30.105";
        for (int i = 0; i < 10; i++) {
            String cityInfoByIp = RegionUtils.getCityInfoByIp(ip);

            System.out.println("cityInfoByIp = " + cityInfoByIp);
        }

    }




}
