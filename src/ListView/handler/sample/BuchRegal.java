package ListView.handler.sample;

/**
     * Von dieser Klasse kann man ein <code>String[][]</code>-Array bekommen, in dem
     * Bücher mit Titel, Autor und Erscheinungsjahr hinterlegt sind...
     */
    public class BuchRegal {

        public static final String[][] BUECHER = {

                {"1984", "George Orwell", "1949"},
                {"Brave New World", "Aldous Huxley", "1932"},
                {"Fahrenheit 451", "Ray Bradbury", "1953"},
                {"Do Androids Dream of Electric Sheep?", "Philip K. Dick", "1968"},
                {"Neuromancer", "William Gibson", "1984"},
                {"Snow Crash", "Neal Stephenson", "1992"},
                {"Daemon", "Daniel Suarez", "2006"},
                {"Ready Player One", "Ernest Cline", "2011"},
                {"War and Peace", "Leo Tolstoy", "1869"},
                {"The Magic Mountain", "Thomas Mann", "1924"},
                {"The Idiot", "Fyodor Dostoyevsky", "1868"}
        };
        public static Buch[] getOutBUECHER() {
            int z = 0;
            final Buch[] buecherArray = new Buch[BUECHER.length];
            for (String[] strings : BUECHER) {
                String[] outBuch = BUECHER[z];
                try {
                    buecherArray[z] = new Buch(outBuch[0], outBuch[1], Integer.parseInt(outBuch[2]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                ++z;
            }
            return buecherArray;
        }
    }


