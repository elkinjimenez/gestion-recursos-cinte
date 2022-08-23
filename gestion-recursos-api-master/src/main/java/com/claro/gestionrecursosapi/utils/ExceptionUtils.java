/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claro.gestionrecursosapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author user
 */
public class ExceptionUtils {
    final static StringWriter stringWriter = new StringWriter();
    final static PrintWriter printWriter = new PrintWriter(stringWriter);

    private static void clearWriters(){
        printWriter.flush();
        stringWriter.getBuffer().setLength(0);
    }

    public static String getStackTraceString(Exception exception) {
        clearWriters();
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    };
    
    public static String printSoapMessage(final SOAPMessage soapMessage) throws TransformerFactoryConfigurationError,
            TransformerConfigurationException, SOAPException, TransformerException
    {
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        // Format it
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        final Source soapContent = soapMessage.getSOAPPart().getContent();

        final ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
        final StreamResult result = new StreamResult(streamOut);
        transformer.transform(soapContent, result);

        return streamOut.toString();
    }
}