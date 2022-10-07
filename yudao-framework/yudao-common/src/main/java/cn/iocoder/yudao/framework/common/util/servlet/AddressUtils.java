package cn.iocoder.yudao.framework.common.util.servlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressUtils {

    private AddressUtils() {}


    /**
     * 根据Ip使用ip2region进行查询地址
     *
     * @param ip ip地址
     * @return 地址信息
     */
    public static String getCityInfoByIp(String ip) {
        // TODO: IP 地址位置
//        try {
//            String path = "ip2region/ip2region.db";
//            String name = "ip2region.db";
//
//            DbConfig config = new DbConfig();
//            File file = ResourceUtil.getResource(path);
//            DbSearcher searcher = new DbSearcher(config, file.getPath());
//            Method method;
//            method = searcher.getClass().getMethod("btreeSearch", String.class);
//            DataBlock dataBlock;
//            dataBlock = (DataBlock) method.invoke(searcher, ip);
//            String address = dataBlock.getRegion().replace("0|", "");
//            if (address.charAt(address.length() - 1) == '|') {
//                address = address.substring(0, address.length() - 1);
//            }
//            return address.equals("内网IP|内网IP") ? "内网IP" : address;
//        } catch (Exception e) {
//            log.error("Get Error In Get City Address ,{}", e.getMessage(), e);
//        }
        return "";
    }


}
