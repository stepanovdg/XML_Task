package com.epam.xmltask.out;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:24 AM
 */
@SuppressWarnings({"ALL"})
public class GoodsOutManager extends OutputStream {
    private static PrintStream prevStream;
    private static GoodsOutManager instance = new GoodsOutManager(prevStream);

    /**
     *
     */

    @Override
    public void write(int b) throws IOException {
        prevStream.write(b);
    }

    /**
     *
     */
    private GoodsOutManager(PrintStream printStr) {
        if (printStr == null) {
            setPrevStream(System.out);
        } else
            setPrevStream(printStr);

    }

    /**
     * @return the instance
     */
    public static GoodsOutManager getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    private static void setInstance(GoodsOutManager instance) {
        GoodsOutManager.instance = instance;
    }

    /**
     */
    public void setPrevStream(PrintStream printStream) {
        if (prevStream != printStream) {
            prevStream = printStream;
            GoodsOutManager.setInstance(new GoodsOutManager(printStream));
        }
    }

    public GoodsOutManager() {

    }

    @Override
    public int hashCode() {
         return prevStream.hashCode();
    }

      public void write(byte[] b) throws IOException {
        prevStream.write(b);
    }

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return prevStream.equals(obj);
    }

    /**
     * @return
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return prevStream.toString();
    }

    /**
     * @see java.io.PrintStream#flush()
     */
    public void flush() {
        prevStream.flush();
    }

    /**
     * @see java.io.PrintStream#close()
     */
    public void close() {
        prevStream.close();
    }

    /**
     * @return
     * @see java.io.PrintStream#checkError()
     */
    public boolean checkError() {
        return prevStream.checkError();
    }

    /**
     * @param buf
     * @param off
     * @param len
     * @see java.io.PrintStream#write(byte[], int, int)
     */
    public void write(byte[] buf, int off, int len) {
        prevStream.write(buf, off, len);
    }

    /**
     * @param b
     * @see java.io.PrintStream#print(boolean)
     */
    public void print(boolean b) {
        prevStream.print(b);
    }

    /**
     * @param c
     * @see java.io.PrintStream#print(char)
     */
    public void print(char c) {
        prevStream.print(c);
    }

    /**
     * @param i
     * @see java.io.PrintStream#print(int)
     */
    public void print(int i) {
        prevStream.print(i);
    }

    /**
     * @param l
     * @see java.io.PrintStream#print(long)
     */
    public void print(long l) {
        prevStream.print(l);
    }

    /**
     * @param f
     * @see java.io.PrintStream#print(float)
     */
    public void print(float f) {
        prevStream.print(f);
    }

    /**
     * @param d
     * @see java.io.PrintStream#print(double)
     */
    public void print(double d) {
        prevStream.print(d);
    }

    /**
     * @param s
     * @see java.io.PrintStream#print(char[])
     */
    public void print(char[] s) {
        prevStream.print(s);
    }

    /**
     * @param s
     * @see java.io.PrintStream#print(java.lang.String)
     */
    public void print(String s) {
        prevStream.print(s);
    }

    /**
     * @param obj
     * @see java.io.PrintStream#print(java.lang.Object)
     */
    public void print(Object obj) {
        prevStream.print(obj);
    }

    /**
     * @see java.io.PrintStream#println()
     */
    public void println() {
        prevStream.println();
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(boolean)
     */
    public void println(boolean x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(char)
     */
    public void println(char x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(int)
     */
    public void println(int x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(long)
     */
    public void println(long x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(float)
     */
    public void println(float x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(double)
     */
    public void println(double x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(char[])
     */
    public void println(char[] x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(java.lang.String)
     */
    public void println(String x) {
        prevStream.println(x);
    }

    /**
     * @param x
     * @see java.io.PrintStream#println(java.lang.Object)
     */
    public void println(Object x) {
        prevStream.println(x);
    }

    /**
     * @param format
     * @param args
     * @return
     * @see java.io.PrintStream#printf(java.lang.String, java.lang.Object[])
     */
    public PrintStream printf(String format, Object... args) {
        return prevStream.printf(format, args);
    }

    /**
     * @param l
     * @param format
     * @param args
     * @return
     * @see java.io.PrintStream#printf(java.util.Locale, java.lang.String, java.lang.Object[])
     */
    public PrintStream printf(Locale l, String format, Object... args) {
        return prevStream.printf(l, format, args);
    }

    /**
     * @param format
     * @param args
     * @return
     * @see java.io.PrintStream#format(java.lang.String, java.lang.Object[])
     */
    public PrintStream format(String format, Object... args) {
        return prevStream.format(format, args);
    }

    /**
     * @param l
     * @param format
     * @param args
     * @return
     * @see java.io.PrintStream#format(java.util.Locale, java.lang.String, java.lang.Object[])
     */
    public PrintStream format(Locale l, String format, Object... args) {
        return prevStream.format(l, format, args);
    }

    /**
     * @param csq
     * @return
     * @see java.io.PrintStream#append(java.lang.CharSequence)
     */
    public PrintStream append(CharSequence csq) {
        return prevStream.append(csq);
    }

    /**
     * @param csq
     * @param start
     * @param end
     * @return
     * @see java.io.PrintStream#append(java.lang.CharSequence, int, int)
     */
    public PrintStream append(CharSequence csq, int start, int end) {
        return prevStream.append(csq, start, end);
    }

    /**
     * @param c
     * @return
     * @see java.io.PrintStream#append(char)
     */
    public PrintStream append(char c) {
        return prevStream.append(c);
    }
}