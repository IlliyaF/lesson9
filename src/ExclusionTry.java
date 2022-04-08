//public class ExclusionTry {
//https://habr.com/ru/company/golovachcourses/blog/223821/
    public class ExclusionTry {
        public static void main(String[] args) throws Throwable {}
    }
//COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'


    class AppA {
    public static void main(String[] args) {
        try {
        } catch (Throwable t) {}
    }
}

//COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'

    class AppB {
        public static void main(String[] args) {
            // Error - потомок Throwable
            throw new Error();
        }
    }
//COMPILATION ERROR: Incompatible types: required 'java.lang.Throwable', found: 'java.lang.String'

    class AppC {
        public static void main(String[] args) {
            throw null;
        }
    }
//RUNTIME ERROR: Exception in thread "main" java.lang.NullPointerException


    class AppD {
        public static void main(String[] args) {
            f(null);
        }
        public static void f(NullPointerException e) {
            try {
                throw e;
            } catch (NullPointerException npe) {
                f(npe);
            }
        }
    }
//RUNTIME ERROR: Exception in thread "main" java.lang.StackOverflowError

    class AppE {
        public static void main(String[] args) {
            Error ref = new Error(); // создаем экземпляр
            throw ref;               // "бросаем" его
        }
    }

// RUNTIME ERROR: Exception in thread "main" java.lang.Error

    class AppF {
        public static void main(String[] args) {
            f(null);
        }
        public static void f(NullPointerException e) {
            try {
                throw e;
            } catch (NullPointerException npe) {
                f(npe);
            }
        }
    }

// RUNTIME ERROR: Exception in thread "main" java.lang.StackOverflowError

    class AppG {
        public static void main(String[] args) {
            System.out.println("sout");
            throw new Error();
        }
    }
// RUNTIME ERROR: Exception in thread "main" java.lang.Error
// sout

    class Appj {
    public static double sqr(double arg) {
        return arg;
    }
}
//COMPILATION ERROR: Incompatible types. Required: double. Found: java.lang.String

    class AppK {
    public static double sqr(double arg) {
        throw new RuntimeException();
    }
}

    class AppJK {
        public static void main(String[] args) {
            Appj.sqr(2.1);
            AppK.sqr(2.34343);
            //AppL.sqr(1.2332);
        }

    }

    /*class AppL {
        public static double sqr(double arg) {// согласно объявлению метода ты должен вернуть double
            long time = System.currentTimeMillis();
            if (time % 2 == 0) {
                return arg * arg;             // ок, вот твой double
            } else if (time % 2 == 1) {
                {
                    while (true) ;                 // не, я решил "повиснуть"
                } //else{
                    throw new RuntimeException(); // или бросить исключение
                }
            }
        }*/
    //}

    /*class AppM {
        public static void main(String[] args) {
            // sqr - "сломается" (из него "выскочит" исключение),
            double d = sqr(10.0);  // выполнение метода main() прервется в этой строчке и
            // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
            System.out.println(d); // и печатать нам ничего не придется!
        }

        public static double sqr(double arg) {
            throw new RuntimeException(); // "бросаем" исключение
        }*/


//RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException


        class AppN {
            public static void main(String[] args) {
                area(-4, -5);
            }

        public static int area (
        int width, int height)

        {
            if (width < 0 || height < 0) {
                throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
            }
            return width * height;
        }
    }

    class AppO {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out");  //вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // вернулись
    } // выходим из текущего фрейма, кончились инструкции

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.RETURN");
            return; // выходим из текущего фрейма по 'return'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
    }
}

         /*#1.in
         .   #2.in
         .   .   #3.in
         .   .   .   #4.in
         .   .   .   #4.RETURN
         .   .   #3.out
         .   #2.out
         #1.out*/

    class AppP {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // ПРОПУСТИЛИ!
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}

        /*>> #1.in
        >> .   #2.in
        >> .   .   #3.in
        >> .   .   .   #4.in
        >> .   .   .   #4.THROW
        >> RUNTIME ERROR: Exception in thread "main" java.lang.Error*/

    class AppQ {
    public static void main(String[] args) {
        System.err.println("#1.in");
        try {
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println("#1.CATCH");  // и работаем
        }
        System.err.println("#1.out");  // работаем дальше
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}

       /* >> #1.in
        >> .   #2.in
        >> .   .   #3.in
        >> .   .   .   #4.in
        >> .   .   .   #4.THROW
        >> #1.CATCH
        >> #1.out*/


    class AppR {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись и работаем
    }

    public static void f() {
        System.err.println(".   #2.in");
        try {
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println(".   #2.CATCH");  // и работаем
        }
        System.err.println(".   #2.out");  // работаем дальше
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        h(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}

        /*>> #1.in
        >> .   #2.in
        >> .   .   #3.in
        >> .   .   .   #4.in
        >> .   .   .   #4.THROW
        >> .   #2.CATCH
        >> .   #2.out
        >> #1.out
*/

    class AppS {
    public static void main(String[] args) {
        System.err.println("#1.in");
        f(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println("#1.out"); // вернулись и работаем
    }

    public static void f() {
        System.err.println(".   #2.in");
        g(); // создаем фрейм, помещаем в стек, передаем в него управление
        System.err.println(".   #2.out"); // вернулись и работаем
    }

    public static void g() {
        System.err.println(".   .   #3.in");
        try {
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
        } catch (Error e) { // "перехватили" "летящее" исключение
            System.err.println(".   .   #3.CATCH");  // и работаем
        }
        System.err.println(".   .   #3.out");  // работаем дальше
    }

    public static void h() {
        System.err.println(".   .   .   #4.in");
        if (true) {
            System.err.println(".   .   .   #4.THROW");
            throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
        }
        System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
    }
}

        /*>> #1.in
        >> .   #2.in
        >> .   .   #3.in
        >> .   .   .   #4.in
        >> .   .   .   #4.THROW
        >> .   .   #3.CATCH
        >> .   .   #3.out
        >> .   #2.out
        >> #1.out*/

    class AppT {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
            System.err.print(" 2");
        }
        System.err.println(" 3");
    }
}

//>> 0 2 3

    class AppV {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Это RuntimeException на самом деле!!!");
            } else {
                System.err.print("В каком смысле не RuntimeException???");
            }
        }
    }
}

//>> Это RuntimeException на самом деле!!!

     class AppU {
    public static void main(String[] args) throws Exception { // пока игнорируйте 'throws'
        try {
            System.err.print(" 0");
            if (true) {throw new Exception();}
            System.err.print(" 1");
        } catch (RuntimeException e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
}

//>> 0
       // >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Exception


 class AppW {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch (Exception e) {
            System.err.print(" 2");
        }
        System.err.print(" 3");
    }
}

//>> 0
       // >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

     class AppX {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw new Error();} // но бросили Error
        }
        System.err.println(" 3");          // пропускаем - уже летит Error
    }
}

//>> 0 2
        //>> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

    class AppY {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw e;}       // и бросили ВТОРОЙ раз ЕГО ЖЕ
        }
        System.err.println(" 3");      // пропускаем - опять летит RuntimeException
    }
}

//>> 0 2
        //>> RUNTIME EXCEPTION: Exception in thread "main" java.lang.RuntimeException

    class AppZ {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) {     // перехватили RuntimeException
            System.err.print(" 2");
            if (true) {throw new Error();} // и бросили новый Error
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }
}

//>> 0 2
       // >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

 class AppAA {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (RuntimeException e) { // перехватили RuntimeException
            System.err.print(" 2.1");
            try {
                System.err.print(" 2.2");
                if (true) {throw new Error();} // и бросили новый Error
                System.err.print(" 2.3");
            } catch (Throwable t) {            // перехватили Error
                System.err.print(" 2.4");
            }
            System.err.print(" 2.5");
        } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
            System.err.print(" 3");
        }
        System.err.println(" 4");
    }
}

//>> 0 2.1 2.2 2.4 2.5 4

    class AppBB {
    public static void main(String[] args) {
        try {
        } catch (RuntimeException e) {
        } catch (Exception e) {
        }
    }
}

//>> COMPILATION ERROR: Exception 'java.lang.RuntimeException' has alredy been caught

 class AppCC {
    public static void main(String[] args) {
        try {
        } catch (Error e) {
        } catch (RuntimeException e) {
        }
    }
}

     class AppDD {
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}

//>> catch Exception
        //>> next statement

    class AppCCC {
    public static void main(String[] args) {
        try {
            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
            throw t;
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}

//>> catch Exception
        //>> next statement

 class AppEE {
    public static void main(String[] args) {
        try {
            System.err.println("try");
        } finally {
            System.err.println("finally");
        }
    }
}

//>> try
        //>> finally

     class AppFF {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
    }
}

        //>> finally
        //>> Exception in thread "main" java.lang.RuntimeException

     class AppGG {
    public static void main(String[] args) {
        try {
            return;
        } finally {
            System.err.println("finally");
        }
    }
}

//>> finally

     class AppJJ {
    public static void main(String[] args) {
        try {
            System.exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}

//>> Process finished with exit code 42

 class AppKK {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}

//>> Process finished with exit code 42

 class AppLL {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().halt(42);
        } finally {
            System.err.println("finally");
        }
    }
}

//>> Process finished with exit code 42

 class AppMM {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {throw new RuntimeException();}
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}

        //>> try
        //>> finally
        //>> Exception in thread "main" java.lang.RuntimeException

 class AppNN {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
        //System.err.println("more");
    }
}

//>> COMPILER ERROR: Unreachable statement

 class AppOO {
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {return;}
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}

//>> try
  //      >> finally

 class AppPP {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}

//>> 1

     class AppQQ {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new RuntimeException();
        } finally {
            return 1;
        }
    }
}

//>> 1

     class AppRR {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            throw new RuntimeException();
        }
    }
}

//>> Exception in thread "main" java.lang.RuntimeException

     class AppSS {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new Error();
        } finally {
            throw new RuntimeException();
        }
    }
}

//>> Exception in thread "main" java.lang.RuntimeException

     class AppTT {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            // nothing
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

//>> 0 1 3 4

 class AppUU {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new Error();}
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

//>> 0 2 3 4

 class AppVV {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    }
}

        //>> 0 3
        //>> RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException

    class AppYY {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                // НИЧЕГО
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - нет исключения
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // заходим - выполнение в норме
        } catch (Exception e) {
            System.err.print(" 6");     // НЕ заходим - нет исключения
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение в норме
    }
}

//>> 0 1 2 4 5 7 8

 class AppZZ {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {throw new RuntimeException();}
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // ЗАХОДИМ - есть исключение
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // заходим - выполнение УЖЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // не заходим - нет исключения, УЖЕ перехвачено
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
    }
}

//>> 0 1 3 4 5 7 8

     class AppAAA {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {throw new Exception();}
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // не заходим - выполнение НЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // ЗАХОДИМ - есть подходящее исключение
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
    }
}

//>> 0 1 4 6 7 8

 class AppBBB {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {throw new Error();}
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // НЕ заходим - выполнение НЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // не заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
        } finally {
            System.err.print(" 7 ");     // заходим всегда
        }
        System.err.print(" 8");         // не заходим - выполнение НЕ в норме
    }
}

        //>> 0 1 4 7
        //>> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error

//https://habr.com/ru/company/golovachcourses/blog/223821/

