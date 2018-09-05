package com.example.hp.sept4try;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpSender {

    final Handler toastHandler = new Handler();

    public void SendTo(final Context context, final Uri uri){

        if (uri == null) return;
        String msg = Uri.decode(uri.getLastPathSegment());

        if(msg == null) return;
        final byte[] msgBytes = msg.getBytes();

        new Thread(new Runnable() {
            public void run() {
                try {
                    InetAddress serverAddress = InetAddress.getByName(uri
                            .getHost());
                    //Log.v(getString(R.string.app_name), serverAddress.getHostAddress());
                    DatagramSocket socket = new DatagramSocket();
                    if (!socket.getBroadcast()) socket.setBroadcast(true);
                    DatagramPacket packet = new DatagramPacket(msgBytes, msgBytes.length,
                            serverAddress, uri.getPort());
                    socket.send(packet);
                    socket.close();
                } catch (final UnknownHostException e) {
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }catch (final SocketException e){

                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }catch (final IOException e){
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

