//package platform.zframe.common.oss;
//
//import platform.zframe.common.utils.ConfigConstant;
//import platform.zframe.common.utils.Constant;
//import platform.zframe.common.utils.SpringContextUtils;
//import platform.zframe.service.SysConfigService;
//
///**
// * 文件上传Factory
// * @author chenyi
// * @email 228112142@qq.com
// * @date 2017-03-26 10:18
// */
//public final class OSSFactory {
//    private static SysConfigService sysConfigService;
//
//    static {
//        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
//    }
//
//    public static CloudStorageService build(){
//        //获取云存储配置信息
//        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
//
//        if(config.getType() == Constant.CloudService.QINIU.getValue()){
//            return new QiniuCloudStorageService(config);
//        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
//            return new AliyunCloudStorageService(config);
//        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
//            return new QcloudCloudStorageService(config);
//        }
//
//        return null;
//    }
//
//}
