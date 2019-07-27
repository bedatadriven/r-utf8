package org.renjin.cran.utf8;

import org.renjin.eval.EvalException;
import org.renjin.primitives.packaging.DllInfo;
import org.renjin.primitives.packaging.DllSymbol;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringArrayVector;
import org.renjin.sexp.StringVector;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.text.Normalizer;

public class utf8 {

    public static void R_init_utf8(DllInfo dll) {

        // Register all methods in this class
        for (Method method : utf8.class.getMethods()) {
            if(method.getName().startsWith("rutf8_")) {
                try {
                    final String methodName = method.getName();
                    final MethodHandle methodHandle = MethodHandles.publicLookup().unreflect(method);
                    final DllSymbol symbol = new DllSymbol(methodName, methodHandle, DllSymbol.Convention.CALL, true);
                    dll.register(symbol);
                } catch (IllegalAccessException e) {
                    throw new EvalException("Cannot access method '%s': %s", method.getName(), e.getMessage(), e);
                }
            }
        }

    }


    public static SEXP rutf8_as_utf8(SEXP x) {
        if(!(x instanceof StringVector)) {
            throw new EvalException("argument is not a character object");
        }
        return x;
    }

    public static SEXP rutf8_utf8_normalize(SEXP x, SEXP map_case, SEXP map_compat,
                                            SEXP map_quote, SEXP remove_ignorable) {

        StringVector vector = (StringVector) x;
        StringArrayVector.Builder output = new StringVector.Builder(0, vector.length());


        for (int i = 0; i < vector.length(); i++) {
            String s = vector.getElementAsString(i);
            if(s != null) {
                s = Normalizer.normalize(s, Normalizer.Form.NFC);
                output.add(s);
            }
        }

        return output.build();
    }

}
