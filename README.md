# SpeechDemo
结合讯飞语音，使用封装好的工具类，把MediaRecord录制好的本地录音amr格式音频转成文字
MediaRecorder的关键设置项代码如下
```Java
MediaRecorder mRecorder = new MediaRecorder();
mRecorder.setMaxDuration(60500);
mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
} else {
      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
}
mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
mRecorder.setOutputFile(mFileName);
mRecorder.prepare();
mRecorder.start();
```

因时间仓促就没有把录音部分放入demo还请见谅，demo中在access中放置了录制好的音频，可以拿出来放到手机sd卡相应目录中，或者自己定义的一个路径使用方法如下，也可以参考[我的博客](http://blog.csdn.net/u010705554/article/details/53189317)  
```Java
/**
     * 工具类
     * @param audioPath
     */
    private void audioDecodeFun(String audioPath){
        audioDecode = AudioDecode.newInstance();
        audioDecode.setFilePath(audioPath);
        audioDecode.prepare();
        audioDecode.setOnCompleteListener(new AudioDecode.OnCompleteListener() {
            @Override
            public void completed(final ArrayList<byte[]> pcmData) {
                if(pcmData!=null){
                    //写入音频文件数据，数据格式必须是采样率为8KHz或16KHz（本地识别只支持16K采样率，云端都支持），位长16bit，单声道的wav或者pcm
                    //必须要先保存到本地，才能被讯飞识别
                    //为防止数据较长，多次写入,把一次写入的音频，限制到 64K 以下，然后循环的调用wirteAudio，直到把音频写完为止
                    for (byte[] data : pcmData){
                        mIat.writeAudio(data, 0, data.length);
                    }
                    Log.d("-----------stop",System.currentTimeMillis()+"");
                    mIat.stopListening();
                }else{
                    mIat.cancel();
                    Log.d(TAG,"--->读取音频流失败");
                }
                audioDecode.release();
            }
        });
        audioDecode.startAsync();
    }
    
```
    
    
