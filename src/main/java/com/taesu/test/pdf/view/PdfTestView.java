package com.taesu.test.pdf.view;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Component("pdfView")
public class PdfTestView extends AbstractPdfView{

    @Autowired
    private ResourceLoader resourceLoader;

    @SuppressWarnings("unused")
    private void createNewPdf(Map<String, Object> model, Document document, PdfWriter writer,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userAgent = request.getHeader("User-Agent");
        String fileName = "test.pdf";

        if(userAgent.indexOf("MSIE") > -1){
            fileName = URLEncoder.encode(fileName, "utf-8");
        }else{
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        final Integer ROW = 3;
        final Integer COL = 2;

        Table table = new Table(COL, ROW);

        Cell cell = new Cell(new Paragraph("셀1"));
        cell.setHeader(true);
        table.addCell(cell);

        cell = new Cell(new Paragraph("셀2"));
        cell.setHeader(true);
        table.addCell(cell);

        for(Integer i=0; i<ROW; i++){
            table.addCell(i.toString());
            table.addCell(new String("test"+i));
        }

        document.add(table);
    }

    @SuppressWarnings("unused")
    private void createNewPdfByTemplate(Map<String, Object> model, Document document, PdfWriter writer,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"pdf-down.pdf\"");
        //이 경우엔 헤더를 다시 설정해주어야 하는 듯

        URL pdfUrl = this.getClass().getClassLoader().getResource("Getting Started.pdf");

        File pdfFile = new ClassPathResource("Getting started.pdf").getFile();
        FileOutputStream pdfOutputStream =new FileOutputStream(pdfFile);
        PdfWriter.getInstance(document, pdfOutputStream);


        File imgFile = new ClassPathResource("test-sign.png").getFile();
        Image img = Image.getInstance(imgFile.toURL());
        img.scaleAbsolute(100, 100);

        document.open();
        Phrase phrase = new Phrase();
        phrase.add(new Chunk(img, 0,0));

        document.add(new Paragraph(phrase));
        document.close();
        pdfOutputStream.close();

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

//		createNewPdf(model,document,writer,request,response);
        createNewPdfByTemplate(model,document,writer,request,response);

    }



}
