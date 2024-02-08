package com.example.thirtyonestudio;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    private String filePath;

    public MyPrintDocumentAdapter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("strukThirtyOneStudio.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .build();

        callback.onLayoutFinished(pdi, true);
    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        if (cancellationSignal.isCanceled()) {
            callback.onWriteCancelled();
            return;
        }

        try {
            // Baca file PDF dan salin ke output stream
            FileInputStream input = new FileInputStream(filePath);
            FileOutputStream output = new FileOutputStream(destination.getFileDescriptor());
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }

            callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

            // Tutup file dan selesai
            input.close();
            output.close();
        } catch (Exception e) {
            callback.onWriteFailed(e.getMessage());
        }
    }
}
