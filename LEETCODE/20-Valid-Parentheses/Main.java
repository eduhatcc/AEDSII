/*
 * CORRIGIR IMPLEMENTAÇÃO
 */

class Solution {
    public boolean isValid(String s) {
        int esqPar = 0,
            dirPar = 0,
            esqCol = 0,
            dirCol = 0,
            esqChv = 0,
            dirChv = 0;
        boolean valid = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') esqPar++;
            else if (s.charAt(i) == ')' && esqCol == dirCol && esqChv == dirChv && esqPar > dirPar) dirPar++;
            else if (s.charAt(i) == '[') esqCol++;
            else if (s.charAt(i) == ']' && esqCol > dirCol) dirCol++; 
            else if (s.charAt(i) == '{') esqChv++;
            else if (s.charAt(i) == '}' && esqChv > dirChv) dirChv++;
        }

        if ((esqPar && dirPar != 0) || 
            esqPar == dirPar && esqCol == dirCol && esqChv == dirChv) valid = true;

        return valid;
    }
}
