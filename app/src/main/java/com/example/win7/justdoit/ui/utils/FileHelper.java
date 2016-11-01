package com.example.win7.justdoit.ui.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.example.win7.justdoit.JustDoItAPP;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件操作工具类
 * @author zhangsl
 *
 */
public class FileHelper {
    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) return false;
        try {
            File file = new File(filePath);
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断手机有无sdcard
     * @return
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
	/**
	 * 写入字符串
	 * @param path
	 * @param str
	 */
    public static void writeStr(String path, String str) {
        File file = new File(path).getParentFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));
            out.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 读取字符串
     * @param path
     * @return
     */
    public static String readStr(String path) {
        File file = new File(path);
        if (file.exists()) {
            StringBuffer sb = new StringBuffer();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int c;
                while ((c = fis.read()) != -1) {
                    sb.append((char) c);
                }
                String str = sb.toString();
                return str;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }
    
    /**
     * @deprecated
     * 将文件转换为base64格式字符串
     * @param path
     * @return
     */
    public static String base64File(String path) {
        File file = new File(path);
        if (file.exists()) {
            return Base64.encodeToString(getFileBytes(file), Base64.DEFAULT);
        } 
        return "";
    }
    
    /** 
     * 获得指定文件的byte数组 (只读前10个字节)
     */  
    public static byte[] getFilePrefix10Bytes(String path){
    	if (TextUtils.isEmpty(path)) return null;
		byte[] b = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			b = new byte[10];
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
    }  
    
    /** 
     * @deprecated
     * 获得指定文件的byte数组(大文件会导致out of memory)
     */  
    public static byte[] getFileBytes(String path){
    	if (TextUtils.isEmpty(path)) return null;
    	return getFileBytes(new File(path));
    }  
    
    /** 
     * @deprecated
     * 获得指定文件的byte数组(大文件会导致out of memory)
     */  
    public static byte[] getFileBytes(File file){
        byte[] b = null;  
        FileInputStream fis = null;
        try {  
            fis = new FileInputStream(file);
            b = new byte[fis.available()];  
            fis.read(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
        return b;  
    }  
    
    /**
     * 根据文件类型获取文件列表
     * @param dir 文件夹目录
     * @param type 文件类型
     * @return 该类型的文件数组
     */
    public static File[] getFilesByType(String dir, final String type) {
        File file = new File(dir);
        if (!file.exists()) {
            return null;
        }
        return file.listFiles(new FilenameFilter() {
            
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(type);
            }
        });
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
        

    /**
     * 删除单个文件
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 判断指定文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExists(String filePath) {
    	if (TextUtils.isEmpty(filePath)) return false;
    	try {
    		File f = new File(filePath);
    		if (f.exists()) {
    			return true;
    		}
    	} catch (Exception e) {
    	}
    	return false;
    }
    
    /**
     * 将文件字节大小转换为包含单位的大小
     * @param bytes
     * @return
     */
	public static String bytes2kb(long bytes) {
		if (bytes == 0) {
			return "0";
		}
		
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
		return (returnValue + "KB");
	}
	
	/**
	 * 判断指定路径的文件大小是否大于指定的MB
	 * @param maxMB
	 * @param path
	 * @return
	 */
	public static boolean isBiggerByMB(int maxMB, String path) {
		if (TextUtils.isEmpty(path)) return false;
		long bytes = getFileSize(new File(path));
		if (bytes == 0) {
			return false;
		}
		
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		if (returnValue > maxMB) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取文件的字节大小
	 * @param file
	 * @return
	 */
	public static long getFileSize(final File file) {
		if (file == null || !file.exists())
			return 0;
		if (!file.isDirectory())
			return file.length();
		final List<File> dirs = new LinkedList<File>();
		dirs.add(file);
		long result = 0;
		while (!dirs.isEmpty()) {
			final File dir = dirs.remove(0);
			if (!dir.exists())
				continue;
			final File[] listFiles = dir.listFiles();
			if (listFiles == null || listFiles.length == 0)
				continue;
			for (final File child : listFiles) {
				result += child.length();
				if (child.isDirectory())
					dirs.add(child);
			}
		}
		return result;
	}

    /**
     * 将流保存至指定文件路径
     * @param inputStream
     * @param dirPrefix
     * @param fileName
     * @return
     */
    public static boolean saveInputStreamToFile(InputStream inputStream, String dirPrefix, String fileName) {
        if (TextUtils.isEmpty(dirPrefix) || TextUtils.isEmpty(fileName) || inputStream == null) return false;
        File dirFile = new File(dirPrefix);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        BufferedOutputStream bos = null;
        boolean rtn = true;
        try {
            File outFile = new File(dirPrefix + File.separator + fileName);
            bos = new BufferedOutputStream(new FileOutputStream(outFile));
            copy(inputStream, bos);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            rtn = false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rtn;
    }

    /**
     * 保存字节数据到指定文件路径
     */
    public static boolean saveDataToFile(byte[] data, String dirPrefix, String fileName) {
        if (TextUtils.isEmpty(dirPrefix) || TextUtils.isEmpty(fileName) || data == null) return false;

        File dirFile = new File(dirPrefix);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        BufferedOutputStream bos = null;
        boolean rtn = true;
        try {
            File myCaptureFile = new File(dirPrefix + "/" + fileName);
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bos.write(data);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            rtn = false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rtn;
    }
	
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static int copy(InputStream input, OutputStream output)
			throws IOException {
		long count = copyLarge(input, output);
		if (count > 2147483647L) {
			return -1;
		}
		return (int) count;
	}

	public static long copyLarge(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

    /**
     * 获取本地sdcard路径
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        } else {
            JustDoItAPP.getInstance().getCacheDir().getAbsolutePath(); // 获取内置内存卡目录
        }
        return sdDir.toString();
    }
}
