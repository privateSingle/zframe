//package platform.zframe.common.oss;
//
//import com.aliyun.oss.OSSClient;
//import platform.zframe.common.oss.RRException
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
///**
// * 阿里云存储
// * @author chenyi
// * @email 228112142@qq.com
// * @date 2017-03-26 16:22
// */
//public class AliyunCloudStorageService extends CloudStorageService{
//    private OSSClient client;
//
//    public AliyunCloudStorageService(CloudStorageConfig config){
//        this.config = config;
//
//        //初始化
//        init();
//    }
//
//    private void init(){
//        client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
//                config.getAliyunAccessKeySecret());
//    }
//
//    @Override
//    public String upload(byte[] data, String path) {
//        return upload(new ByteArrayInputStream(data), path);
//    }
//
//    @Override
//    public String upload(InputStream inputStream, String path) {
//        try {
//            client.putObject(config.getAliyunBucketName(), path, inputStream);
//        } catch (Exception e){
//            throw new RRException("上传文件失败，请检查配置信息", e);
//        }
//
//        return config.getAliyunDomain() + "/" + path;
//    }
//
//    @Override
//    public String upload(byte[] data) {
//        return upload(data, getPath(config.getAliyunPrefix()));
//    }
//
//    @Override
//    public String upload(InputStream inputStream) {
//        return upload(inputStream, getPath(config.getAliyunPrefix()));
//    }
//}
