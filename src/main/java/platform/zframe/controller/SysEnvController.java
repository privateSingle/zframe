package platform.zframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.zframe.common.utils.DateUtil;
import platform.zframe.common.utils.LocalDateTimeUtils;
import platform.zframe.common.utils.PageUtils;
import platform.zframe.common.utils.R;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
@Configuration
@Controller
@RequestMapping("/sys/env")
public class SysEnvController {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${server.port}")
    private String AppPort;
    @Value("${spring.redis.host}")
    private String redisUrl;
    @Value("${spring.redis.port}")
    private String redisport;
    @Value("${spring.redis.database}")
    private String redisDBIndex;
    @RequestMapping("")
    public String list() {
        return "zframe/sysenv/index";
    }

    @ResponseBody
    @RequestMapping("/listData")
    public R listData() throws Exception {
        List<Envinfo> envinfoList=new ArrayList<Envinfo>();
        Runtime r=Runtime.getRuntime();
        Properties props = System.getProperties();
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        envinfoList.add(new Envinfo("系统名称","Zframe"));
        envinfoList.add(new Envinfo("FrameWork Support","Zframe v2.0  Power by ZhangYanTao e-mail: zytzhangyantao@163.com  Base on SpringBoot v1.5.9"));
        envinfoList.add(new Envinfo("工程信息", props.getProperty("user.dir")));
        //拿到系统参数
        Map<String, String> map = System.getenv();
        envinfoList.add(new Envinfo("服务器信息", os.getName()+"   v:"+os.getVersion()+" "+os.getArch()+"  cpu: "+os.getAvailableProcessors()));
        envinfoList.add(new Envinfo("IPV4地址", getLocalHostLANAddress().getHostAddress()));
        envinfoList.add(new Envinfo("应用端口", AppPort));
        envinfoList.add(new Envinfo("JRE路径", props.getProperty("java.home")));
        envinfoList.add(new Envinfo("JVM", props.getProperty("java.vendor")+"  "+props.getProperty("java.version")));
        envinfoList.add(new Envinfo("JVM启动时间", LocalDateTimeUtils.getTimeStampAsString(bean.getStartTime(),"yyyy-MM-dd HH:mm:ss")));
        envinfoList.add(new Envinfo("JVM运行时长", DateUtil.formatTime(bean.getUptime())));
        envinfoList.add(new Envinfo("临时文件路径", props.getProperty("java.io.tmpdir")));
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        vmTotal = r.totalMemory() / byteToMb;
        vmFree = r.freeMemory() / byteToMb;
        vmMax = r.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        envinfoList.add(new Envinfo("JVM内存 已用/空闲/总内存/最大内存",vmUse+"MB / "+vmFree+"MB / "+vmTotal+"MB / "+vmMax+"MB"));
        com.sun.management.OperatingSystemMXBean osmx=(com.sun.management.OperatingSystemMXBean)sun.management.ManagementFactoryHelper.getOperatingSystemMXBean();
        long physicalFree = osmx.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = osmx.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;
        envinfoList.add(new Envinfo("OS内存  已用/空闲/总内存",physicalFree+"MB / "+physicalUse+"MB / "+physicalTotal+"MB"));
        envinfoList.add(new Envinfo("DataBase-URl",dbUrl));
        envinfoList.add(new Envinfo("Redis服务器","DBindex: "+redisDBIndex+"  ServerUrL:"+redisUrl+"  port:"+redisport));
        PageUtils pageUtils=new PageUtils(envinfoList,envinfoList.size(),1,1);
        return R.ok().put("page",pageUtils);
    }
    public static InetAddress getLocalHostLANAddress() throws Exception {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static class Envinfo implements Serializable {
        public Envinfo(String item, String value) {
            this.item = item;
            this.value = value;
        }

        private String item;
        private String value;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Envinfo{" +
                    "item='" + item + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
