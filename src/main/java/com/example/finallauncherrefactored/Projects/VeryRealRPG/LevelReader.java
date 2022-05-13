package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;

import java.io.Reader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class LevelReader {

    public static String[] TEMPlevels = new String[]{"Levels/level0.txt", "Levels/level1.txt", "Levels/level2.txt", "C:/Coding/Test/Levels/level3.txt","C:/Coding/Test/Levels/level4.txt", "C:/Coding/Test/Levels/level5.txt",
            "C:/Coding/Test/Levels/level6.txt", "C:/Coding/Test/Levels/level7.txt", "C:/Coding/Test/Levels/level8.txt", "C:/Coding/Test/Levels/level9.txt", "C:/Coding/Test/Levels/level10.txt",
            "C:/Coding/Test/Levels/level11.txt", "C:/Coding/Test/Levels/level12.txt", "C:/Coding/Test/Levels/level13.txt", "C:/Coding/Test/Levels/level14.txt"};

    public static String[] levels = new String[]
            {       Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level0.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level1.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level2.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level3.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level4.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level5.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level6.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level7.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level8.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level9.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level10.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level11.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level12.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level13.txt")).getPath(),
                    Objects.requireNonNull(Main.class.getResource("VeryRealRPG/Levels/level14.txt")).getPath()
            };

    public static char[][] main(String[] files) throws IOException {

        Charset encoding = Charset.defaultCharset();
        char[][] levels = new char[15][];
        int i = 0;
        for (String filename : files) {

            File file = new File(filename);
            levels[i] = handleFile(file, encoding);
            i++;
        }
        return levels;
    }

    private static char[] handleFile(File file, Charset encoding)
            throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, encoding);

             Reader buffer = new BufferedReader(reader)) {
            return handleCharacters(buffer);
        }
    }

    private static char[] handleCharacters(Reader reader)
            throws IOException {
        int r;
        int i = 0;
        char[] lev = new char[257];
        while ((r = reader.read()) != -1) {

            char ch = (char) r;

            lev[i] = ch;
            i++;
        }
        return lev;
    }
}
