package com.example.hi.album;

import android.support.media.ExifInterface;

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


    private String getExif(ExifInterface exif) {
        StringBuilder builder = new StringBuilder();

        //https://nominatim.openstreetmap.org/reverse?format=json&lat=<LATITUDE>&lon=<LONGITUDE>&zoom=18&addressdetails=0
        String latitudeRef, longitudeRef;
        Float latitude, longitude;
        latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
        if (latitudeRef.equals("N")) {
            latitude = convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
        } else {
            latitude = 0 - convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
        }
        longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
        if (longitudeRef.equals("E")) {
            longitude = convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
        } else {
            longitude = 0 - convertFromDegreeMinuteSeconds(exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
        }

        builder.append("Exif : \n");
        builder.append("Date & Time: " + getExifTag(exif, ExifInterface.TAG_DATETIME) + "\n");
        builder.append("Flash: " + getExifTag(exif, ExifInterface.TAG_FLASH) + "\n");
        builder.append("Focal Length: " + getExifTag(exif, ExifInterface.TAG_FOCAL_LENGTH) + "\n");
        builder.append("GPS Datestamp: " + getExifTag(exif, ExifInterface.TAG_GPS_DATESTAMP) + "\n");
        builder.append("GPS Latitude: " + latitude + "\n");
        builder.append("GPS Latitude Ref: " + getExifTag(exif, ExifInterface.TAG_GPS_LATITUDE_REF) + "\n");
        builder.append("GPS Longitude: " + longitude + "\n");
        builder.append("GPS Longitude Ref: " + getExifTag(exif, ExifInterface.TAG_GPS_LONGITUDE_REF) + "\n");
        builder.append("GPS Processing Method: " + getExifTag(exif, ExifInterface.TAG_GPS_PROCESSING_METHOD) + "\n");
        builder.append("GPS Timestamp: " + getExifTag(exif, ExifInterface.TAG_GPS_TIMESTAMP) + "\n");
        builder.append("Image Length: " + getExifTag(exif, ExifInterface.TAG_IMAGE_LENGTH) + "\n");
        builder.append("Image Width: " + getExifTag(exif, ExifInterface.TAG_IMAGE_WIDTH) + "\n");
        builder.append("Camera Make: " + getExifTag(exif, ExifInterface.TAG_MAKE) + "\n");
        builder.append("Camera Model: " + getExifTag(exif, ExifInterface.TAG_MODEL) + "\n");
        builder.append("Camera Orientation: " + getExifTag(exif, ExifInterface.TAG_ORIENTATION) + "\n");
        builder.append("Camera White Balance: " + getExifTag(exif, ExifInterface.TAG_WHITE_BALANCE) + "\n");

        return builder.toString();
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

        return (null != attribute ? attribute : "");
    }
}
