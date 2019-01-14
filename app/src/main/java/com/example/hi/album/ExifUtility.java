package com.example.hi.album;

import android.graphics.ColorSpace;
import android.support.media.ExifInterface;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.hi.album.MainActivity.viewPager;

public class ExifUtility {
    //        try {
//            ExifInterface exif = new ExifInterface(AnhFragment.mangHinh.get(viewPager.getCurrentItem()).duongdan);
//            File f = new File(AnhFragment.mangHinh.get(viewPager.getCurrentItem()).duongdan);
//            Log.d("Exif", getExif(exif) + "\n Date: " + (new Date(f.lastModified())).toString() + "\n File Size: " + f.length() / 1024 + "KB\n");
//            String response = new LocationFetch(10.812775f,106.71379f).execute().get();
//            Log.d("Response","Response = " + response);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


    public List<String> getExif(ExifInterface exif) {

        List<String> exifList = new ArrayList<>();

//        StringBuilder builder = new StringBuilder();
//
//        String latitudeRef, longitudeRef;
//        Float latitude, longitude;
//        latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
//        if (latitudeRef.equals("N")) {
//            latitude = convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
//        } else {
//            latitude = 0 - convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
//        }
//        longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
//        if (longitudeRef.equals("E")) {
//            longitude = convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
//        } else {
//            longitude = 0 - convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
//        }

        File f = new File(AnhFragment.mangHinh.get(viewPager.getCurrentItem()).duongdan);

        if (!getExifTag(exif, ExifInterface.TAG_DATETIME).isEmpty()){
            exifList.add("Date: " + getExifTag(exif, ExifInterface.TAG_DATETIME));
        }
        else {
            DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
            exifList.add("Date: " + dateTimeInstance.format(new Date(f.lastModified())));
        }
        if (!getExifTag(exif, ExifInterface.TAG_FILE_SOURCE).isEmpty()){
            exifList.add("File Source: " + getExifTag(exif, ExifInterface.TAG_FILE_SOURCE));
        }
        exifList.add("File Size: " + f.length() / 1024 + "KB");

        if (!getExifTag(exif, ExifInterface.TAG_IMAGE_WIDTH).isEmpty() && !getExifTag(exif, ExifInterface.TAG_IMAGE_LENGTH).isEmpty()){
            exifList.add("Image Size: " + getExifTag(exif, ExifInterface.TAG_IMAGE_WIDTH) + "x" + getExifTag(exif, ExifInterface.TAG_IMAGE_LENGTH));
        }
        if (!getExifTag(exif, ExifInterface.TAG_MAKE).isEmpty()){
            exifList.add("Make: " + getExifTag(exif, ExifInterface.TAG_MAKE));
        }
        if (!getExifTag(exif, ExifInterface.TAG_MODEL).isEmpty()){
            exifList.add("Model: " + getExifTag(exif, ExifInterface.TAG_MODEL));
        }
        if (!getExifTag(exif, ExifInterface.TAG_WHITE_BALANCE).isEmpty()){
            exifList.add("White Balance: " + getExifTag(exif, ExifInterface.TAG_WHITE_BALANCE));
        }
        if (!getExifTag(exif, ExifInterface.TAG_BRIGHTNESS_VALUE).isEmpty()){
            exifList.add("Brightness: " + getExifTag(exif, ExifInterface.TAG_BRIGHTNESS_VALUE));
        }
        if (!getExifTag(exif, ExifInterface.TAG_CONTRAST).isEmpty()){
            exifList.add("Contrast: " + getExifTag(exif, ExifInterface.TAG_CONTRAST));
        }
        if (!getExifTag(exif, ExifInterface.TAG_GAMMA).isEmpty()){
            exifList.add("Gamma: " + getExifTag(exif, ExifInterface.TAG_GAMMA));
        }
        if (!getExifTag(exif, ExifInterface.TAG_SATURATION).isEmpty()){
            exifList.add("Saturation: " + getExifTag(exif, ExifInterface.TAG_SATURATION));
        }
        if (!getExifTag(exif, ExifInterface.TAG_SHARPNESS).isEmpty()){
            exifList.add("Sharpness: " + getExifTag(exif, ExifInterface.TAG_SHARPNESS));
        }
        if (!getExifTag(exif, ExifInterface.TAG_FOCAL_LENGTH).isEmpty()){
            exifList.add("Focal Length: " + getExifTag(exif, ExifInterface.TAG_FOCAL_LENGTH));
        }
        if (!getExifTag(exif, ExifInterface.TAG_ISO_SPEED).isEmpty()){
            exifList.add("ISO Speed: " + getExifTag(exif, ExifInterface.TAG_ISO_SPEED));
        }
        if (!getExifTag(exif, ExifInterface.TAG_SHUTTER_SPEED_VALUE).isEmpty()){
            exifList.add("Shutter Speed: " + getExifTag(exif, ExifInterface.TAG_SHUTTER_SPEED_VALUE));
        }

//        builder.append("Exif : \n");
//        builder.append("Date & Time: ").append(getExifTag(exif, ExifInterface.TAG_DATETIME)).append("\n");
//        builder.append("File Source: " + getExifTag(exif, ExifInterface.TAG_FILE_SOURCE) + "\n");
//        builder.append("Image Length: " + getExifTag(exif, ExifInterface.TAG_IMAGE_LENGTH) + "\n");
//        builder.append("Image Width: " + getExifTag(exif, ExifInterface.TAG_IMAGE_WIDTH) + "\n");
//        builder.append("Camera Make: " + getExifTag(exif, ExifInterface.TAG_MAKE) + "\n");
//        builder.append("Camera Model: " + getExifTag(exif, ExifInterface.TAG_MODEL) + "\n");
//
//
//        builder.append("Color Space: " + getExifTag(exif, ExifInterface.TAG_COLOR_SPACE) + "\n");
//        builder.append("White Balance: " + getExifTag(exif, ExifInterface.TAG_WHITE_BALANCE) + "\n");
//        builder.append("Brightness: " + getExifTag(exif, ExifInterface.TAG_BRIGHTNESS_VALUE) + "\n");
//        builder.append("Contrast: " + getExifTag(exif, ExifInterface.TAG_CONTRAST) + "\n");
//        builder.append("Gamma: " + getExifTag(exif, ExifInterface.TAG_GAMMA) + "\n");
//        builder.append("SATURATION: " + getExifTag(exif, ExifInterface.TAG_SATURATION) + "\n");
//        builder.append("SHARPNESS: " + getExifTag(exif, ExifInterface.TAG_SHARPNESS) + "\n");
//
//        builder.append("Flash: " + getExifTag(exif, ExifInterface.TAG_FLASH) + "\n");
//        builder.append("Focal Length: " + getExifTag(exif, ExifInterface.TAG_FOCAL_LENGTH) + "\n");
//        builder.append("ISO_SPEED: " + getExifTag(exif, ExifInterface.TAG_ISO_SPEED) + "\n");
//        builder.append("SHUTTER_SPEED: " + getExifTag(exif, ExifInterface.TAG_SHUTTER_SPEED_VALUE) + "\n");
//
//        return builder.toString();

        return exifList;
    }

    private static Float convertFromDegreeMinuteSeconds(String stringDMS) {
        Float result;
        String[] DMS = stringDMS.split(",", 3);
        String[] stringD = DMS[0].split("/", 2);
        Double D0 = Double.valueOf(stringD[0]);
        Double D1 = Double.valueOf(stringD[1]);
        Double FloatD = D0 / D1;
        String[] stringM = DMS[1].split("/", 2);
        Double M0 = Double.valueOf(stringM[0]);
        Double M1 = Double.valueOf(stringM[1]);
        Double FloatM = M0 / M1;
        String[] stringS = DMS[2].split("/", 2);
        Double S0 = Double.valueOf(stringS[0]);
        Double S1 = Double.valueOf(stringS[1]);
        Double FloatS = S0 / S1;
        result = (float) (FloatD + (FloatM / 60) + (FloatS / 3600));
        return result;
    }

    private String getExifTag(ExifInterface exif, String tag) {
        String attribute = exif.getAttribute(tag);

        return (null != attribute && !attribute.equals("0") ? attribute : "");
    }
}
