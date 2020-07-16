package com.madd.template.utils;


import java.io.IOException;
import java.net.*;

/**
 * Created by 27528 on 2019/6/28.
 * @author madd
 */
public class SocketUtils {

    /**
     * 判断某终端服务能否连通
     *
     * @param host host
     * @param port port
     * @return Result
     */
    public static Result isRunning(String host, int port) {
        Socket sClient = null;
        Result result=new Result();
        try {
            SocketAddress saAdd = new InetSocketAddress(host.trim(), port);
            sClient = new Socket();
            sClient.connect(saAdd, 1000);
        }
        catch (UnknownHostException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }
        catch (SocketTimeoutException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }
        catch (IOException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }
        catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }
        finally {
            try {
                if (sClient != null) {
                    sClient.close();
                }
            }
            catch (Exception e) {
            }
        }
        result.setSuccess(true);
        result.setMessage("终端连接成功！");
        return result;
    }

}
