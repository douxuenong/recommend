package cn.shawn.recommend.bean;

import com.alibaba.fastjson.JSONArray;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockettoPython {
    //传递信息
    public JSONArray sendMessage(byte[] hostID){
        String ip = "localhost";    //地址
        int port = 12345;    //端口号
        Socket socket = null;
        try {
            //连接服务器
            socket = new Socket(ip,port);
            //获取数据流
            InputStream in = socket.getInputStream();
            //获取输出流
            OutputStream out = socket.getOutputStream();
            // 包装输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //发送数据用户名
            out.write(hostID);
            //构建列表
            String tmp = null;
            StringBuffer sb = new StringBuffer();
            // 接受应答
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            // 解析结果
            JSONArray res = JSONArray.parseArray(sb.toString());
            return res;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONArray.parseArray("fail");
    }
}

