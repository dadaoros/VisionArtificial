package com.project.coursera.dailyselfie;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by root on 21/03/15.
 */
public class SelfieFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.getName().contains("SELFIE"))return true;
        return false;
    }
}
