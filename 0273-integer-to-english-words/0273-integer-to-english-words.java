class Solution {
    private static final String[] BELOW_TWENTY = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] SCALES = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        StringBuilder result = new StringBuilder();
        int scaleIndex = 0;
        String[] chunks = new String[4];
        int chunkTotal = 0;

        while (num > 0) {
            int part = num % 1000;
            if (part != 0) {
                String piece = spellUnderThousand(part).trim();
                chunks[chunkTotal++] = SCALES[scaleIndex].isEmpty() ? piece : piece + " " + SCALES[scaleIndex];
            }
            num /= 1000;
            scaleIndex++;
        }

        for (int i = chunkTotal - 1; i >= 0; i--) {
            result.append(chunks[i]);
            if (i > 0) result.append(' ');
        }
        return result.toString();
    }

    private String spellUnderThousand(int value) {
        StringBuilder sb = new StringBuilder();
        if (value >= 100) {
            sb.append(BELOW_TWENTY[value / 100]).append(" Hundred");
            value %= 100;
            if (value > 0) sb.append(' ');
        }
        if (value >= 20) {
            sb.append(TENS[value / 10]);
            value %= 10;
            if (value > 0) sb.append(' ');
        }
        if (value > 0) {
            sb.append(BELOW_TWENTY[value]);
        }
        return sb.toString();
    }
}