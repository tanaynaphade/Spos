import java.util.*;

class Symbol {
    String name;
    int address;

    Symbol(String name, int address) {
        this.name = name;
        this.address = address;
    }
}

class Instruction {
    String opcode;
    String operand;

    Instruction(String opcode, String operand) {
        this.opcode = opcode;
        this.operand = operand;
    }

    String toMachineCode(Map<String, Integer> symbolTable) {
        int operandAddress = symbolTable.getOrDefault(operand, -1);
        if (operandAddress == -1) {
            throw new IllegalArgumentException("Undefined symbol: " + operand);
        }
        return String.format("%03d %03d", Integer.parseInt(opcode), operandAddress);
    }
}

class Assembler {
    private List<Instruction> instructions;
    private Map<String, Integer> symbolTable;

    Assembler() {
        instructions = new ArrayList<>();
        symbolTable = new HashMap<>();
    }

    void addSymbol(String name, int address) {
        symbolTable.put(name, address);
    }

    void addInstruction(String opcode, String operand) {
        instructions.add(new Instruction(opcode, operand));
    }

    void assemble() {
        System.out.println("Machine Code:");
        for (Instruction instruction : instructions) {
            String machineCode = instruction.toMachineCode(symbolTable);
            System.out.println(machineCode);
        }
    }
}

public class TwoPassAssembler {
    public static void main(String[] args) {
        // Pass I: Building the symbol table (hardcoded for illustration)
        Assembler assembler = new Assembler();
        assembler.addSymbol("START", 0);
        assembler.addSymbol("LOOP", 3);
        assembler.addSymbol("END", 5);

        // Pass II: Adding instructions
        assembler.addInstruction("1", "START");  // 1: LOAD
        assembler.addInstruction("2", "LOOP");   // 2: STORE
        assembler.addInstruction("3", "END");    // 3: JUMP

        // Assembling and printing machine code
        assembler.assemble();
    }
}
