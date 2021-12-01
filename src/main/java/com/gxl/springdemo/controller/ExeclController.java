package com.gxl.springdemo.controller;

import com.csvreader.CsvReader;
import com.gxl.springdemo.pojo.WhiteDo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/execl")
@Slf4j
public class ExeclController {

    private static AtomicInteger count = new AtomicInteger(0);

    @PostMapping("/importData")
    public void importData(MultipartFile file){
        //导入文件
        String load = new ApplicationHome(getClass()).getSource().getParentFile().getPath();   //获取当前项目根目录环境
        load = load + "/upload/";
//        String load = "upload";

        //这一步是处理上传文件目录是否存在
        File fileUrl = new File(load);
        if (!fileUrl.exists()) {
            fileUrl.mkdirs();
        }
        //校验上传文件是否存在
        if (file.isEmpty()) {
//            throw new CommonException(EcExceptionEnum.UPLOAD_ERROR);
        }
        //校验文件大小getSize()的单位是字节(Byte)(1MB=1024KB=1024*1024Byte)
//        if (file.getSize() > 1024 * 1024 * 10) {   //要小于10MB
//            throw new CommonException(EcExceptionEnum.UPLOAD_BIG);
//        }
        //获取文件后缀
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //组装文件的名称(这里可以使用UUID)
        String fileName = new Date().getTime()+fileType;
        log.info("导入文件名称:{}",fileName);
        File dest = new File(load + fileName);
        try {
            file.transferTo(dest);
//            return fileName;
        } catch (IOException e) {
//            log.error(e.toString(), e);
//            throw new CommonException(EcExceptionEnum.UPLOAD_ERROR);
        }
        //todo:删除文件
    }


    //todo:添加数据,过滤数据,失败数据
    @GetMapping("/read")
    public String handleData(){
        Long start = new Date().getTime();
        if(count.get() != 0){
            return "有数据未导入完成,已处理数量:"+count.get();
        }

        String load = new ApplicationHome(getClass()).getSource().getParentFile().getPath();   //获取当前项目根目录环境
        load = load + "/upload/";

        //读取文件
        CsvReader csvReader = null;
        ExecutorService executorService = null;
        try{
            csvReader = new CsvReader(load + "1629982342942.csv",',', Charset.forName("UTF-8"));
            csvReader.readHeaders();
            List<Future<Boolean>> futureList = new ArrayList<Future<Boolean>>();
            executorService = Executors.newFixedThreadPool(5);
            //分页读取数据后,加入线程池处理
            while (this.getPageUserList(executorService,futureList,csvReader)) {}
            for (Future<Boolean> future : futureList) {
                future.get();
            }
            //todo 释放公用字段

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            csvReader.close();
            executorService.shutdown();
            //重置
            count.getAndSet(0);
        }
        Long end = new Date().getTime();
        log.info(end-start+"");
        return "结束";
    }

    private boolean getPageUserList(ExecutorService executorService,List<Future<Boolean>> futureList,CsvReader csvReader) throws IOException{
        // 读表头
        List<WhiteDo> whiteDoList = new ArrayList<>();
        while (csvReader.readRecord()){
            WhiteDo whiteDo = this.formatData(csvReader.getRawRecord());
            if(whiteDo == null){
                continue;
            }
            whiteDoList.add(whiteDo);
            if(whiteDoList.size() == 5){
                break;
            }
        }
        if(!whiteDoList.isEmpty()){
            count.getAndAdd(whiteDoList.size());
            Future<Boolean> future = executorService.submit(new WhiteTask(whiteDoList));
            futureList.add(future);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * 格式化行数据
     * @param rawRecord
     * @return
     */
    private WhiteDo formatData(String rawRecord){
        if(StringUtils.isNotBlank(rawRecord)){
            String[] raws = rawRecord.split("\t");
            if(raws.length ==6){
                WhiteDo whiteDo = new WhiteDo();
                whiteDo.setRaw_query(raws[0]);
                whiteDo.setLib_id(raws[1]);
                whiteDo.setCategory(raws[2]);
                whiteDo.setBusiness_id(raws[3]);
                whiteDo.setIntent_name(raws[4]);
                whiteDo.setSegmented_query(raws[5]);
                return whiteDo;
            }
        }
        return null;
    }

}
