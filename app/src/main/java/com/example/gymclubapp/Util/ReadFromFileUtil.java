package com.example.gymclubapp.util;

import android.util.Log;
import android.util.Xml;

import com.example.gymclubapp.entity.CourseContent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFromFileUtil {

    public static CourseContent getCourseContent(InputStream inputStream) {

        String courseTitle = new String();
        String trainingPart = new String();
        String courseInstruction = new String();

        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));

            String line;        // 储存逐行读取的数据
            int count = 1;      // 计数

            while ((line = buff.readLine()) != null) {
                if (count == 1) {
                    courseTitle = line;
                } else if (count == 2) {
                    trainingPart = line;
                } else {
                    courseInstruction = courseInstruction + line + "\n";
                }
                count++;
            }
            buff.close();
        } catch (IOException e) {
            Log.d("ReadFromFileUtil", "getCourseContent: " + e.toString());
        }

        return new CourseContent(courseTitle, trainingPart, courseInstruction);
    }
}
