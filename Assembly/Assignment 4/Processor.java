package procsimulator;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

/** Simulation of MIPS-like processor
 *
 * Input: Hexadecimal instructions in a text file
 * Output: Display of processor state in a text file
*/

class Processor {

    /** program counter is incremented on each fetch */
    private int pc;

    /** instruction register to hold hexadecimal instruction */
    private String ir;

    /** instruction register subfields */
    private int ir31_26, ir25_21, ir20_16, ir15_0, ir15_11, ir5_0;

    /** results from reading registers (sources) */
    private int readData1, readData2;

    /** ALU result */
    private int aluOut;

    /** data memory read result */
    private int memOut;

    /** control logic settings
     *  [0] = RegDest
     *  [1] = ALUSrc
     *  [2] = MemToReg
     *  [3] = RegWrite
     *  [4] = MemRead
     *  [5] = MemWrite
     *  [6] = Branch
     *  [7] = ALUOp1
     *  [8] = ALUOp0
    */
    private int[] control;

    /** 4 bit ALU control settings */
    private int[] aluControl;

    /** 32 address and data registers */
    private int[] regs;

    /** data memory */
    private int[] dataMem;

    /** instruction memory */
    private String[] instrMem;

    /** maximum memory size */
    private final int MAXMEM = 20;

    Processor() {
        pc = 0;
        control = new int[9];
        aluControl = new int[4];
        regs = new int[32];
        dataMem = new int[MAXMEM];
        instrMem = new String[MAXMEM];
        String infile = JOptionPane.showInputDialog ("Enter input file name");
        try {
             File file = new File(infile);
             Scanner scanner = new Scanner(file);
             int index = 0;
             while (scanner.hasNext() && index < MAXMEM) {
               instrMem[index] = scanner.next();
               index++;
             }
             for (int loop = index; index < MAXMEM; index++)
                 instrMem[index] = new String("00000000");
             scanner.close();
         } catch (FileNotFoundException e) {
              e.printStackTrace();
         }
    }

/** simulation of processor
 *  Process will continuously fetch and execute code until
 *  a zero instruction is read.
 */
     public void go () {
        try {
            String outfile = JOptionPane.showInputDialog
                    ("Enter output file name");
            PrintStream output = new PrintStream
                 (new FileOutputStream(outfile));
            fetch();
            while (!ir.equals("00000000")) {
                 decode();
                 setControl();
                 readRegs();
                 setALUControl();
                 performALUOp();
                 accessMemory();
                 writeBack();
                 printState (output);
                 fetch();
             }
                 output.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }

     /** print the state of the processor including
      * pc, ir, registers and data memory
      *
      * @param outfile text file for output
      */
     private void printState (PrintStream out)  {
         out.print("pc = " + pc + "  ir = " + ir +
                 "   control = ");
         for (int val : control)
             out.print (val);
         out.print ("   ALUcontrol = ");
         for (int val : aluControl)
             out.print (val);
         out.println ("");

         out.print ("ir fields");
         out.println (ir31_26 + "/" + ir25_21 + "/" +
                 ir20_16 + "/" + ir15_11 + "/" + ir5_0
                 + "/" + ir15_0);
         int index;
         for (index = 0; index < 32; index++) {
             out.print ("R[" + index + "]" + regs[index]);
             out.print ("   ");
             if (index % 8 == 7)
                 out.println ("");
         }

         for (index = 0; index < MAXMEM; index++) {
             out.print ("M[" + index + "]" + dataMem[index]);
             out.print ("    ");
             if (index % 8 == 7)
                 out.println ("");
          }

         for (index = 1; index <= 3; index++)
             out.println ("");
     }

     /** Break down the hexadecimal digits in the IR
      * and convert to integers setting IR subfields
      */

     private void decode () {

         int [] nums = new int [8];
         for (int index = 0; index < 8; index++) {
             char ch = ir.charAt(index);
             if (ch >= '0' && ch <= '9')
                 nums[index] = ch - '0';
             else
                 nums[index] = ch - 'A' + 10;
         }

         ir31_26 = nums[0] * 4 + nums[1] / 4;
         ir25_21 = nums[1] % 4 * 8 + nums[2] / 2;
         ir20_16 = nums[2] % 2 * 16 + nums[3];
         ir15_11 = nums[4] * 2 + nums[5] / 8;
         ir5_0 = nums[6] % 4 * 16 + nums[7];
         ir15_0 = nums[4];
         for (int pos = 5; pos <= 7; pos++)
             ir15_0 = ir15_0 * 16 + nums[pos];
         if (nums[4] >= 8)
             ir15_0 -= 65536;     // convert to negative
     }

     /** read an instruction from memory and place it into
      * the instruction register and then increment the pc by 1.
      */
     private void fetch() {
         ir = instrMem[pc];
         pc++;
     }

     /** based on the opcode in ir31_26, set the 9 control
      * signals as defined in the text
      */

     private void setControl () {
         for (int i = 0; i < 9; i++)
             control[i] = 0;
         switch (ir31_26) {
             case 0: //add
                 control[0] = 1;
                 control[3] = 1;
                 control[7] = 1;
                 break;
             case 8: //addi
                 control[1] = 1;
                 control[3] = 1;
                 break;
             case 43: // sw
                 control[1] = 1;
                 control[5] = 1;
                 break;
             case 35: // lw
                 control[1] = 1;
                 control[2] = 1;
                 control[3] = 1;
                 control[4] = 1;
                 break;
         }
     }

     /** based on the control signals and function code
      *  in ir5_0, set the control signals for the ALU
      */

     private void setALUControl() {
         for (int i = 0; i < 4; i++)
             aluControl[i] = 0;
         if (control[7] == 0 && control[8] == 0)
             aluControl[2] = 1;
         else if (control[7] == 1 && control[8] == 0)
             if (ir5_0 == 32)
                aluControl[2] = 1;
         else if (ir5_0 == 34) {
                aluControl[2] = 1;
                aluControl[1] = 1;
             }
     }

     /** perform the ALU function based on the ALU control
      *  signals.  Input can be from with readData2 or from
      *  the sign extended ir15_0.
      */

     private void performALUOp() {
        int temp;
        if (control[1] == 0)
            temp = readData2;
        else
            temp = ir15_0;
        if (aluControl[2] == 1 && aluControl[1] == 0)
            aluOut = readData1 + temp;
        else
            if (aluControl[2] == 1 && aluControl[1] == 1)
                aluOut = readData1 - temp;
            else
                aluOut = readData1 & temp;
   }

     /** Perform the memory read or write as determined
      *  by the control signals.
      */

     private void accessMemory() {
        if (control[4] == 1) // read
            memOut = dataMem[aluOut];
         else if (control[5] == 1) //write
           dataMem[aluOut] = readData2;
     }

     /** Write to the destination register as
      *  controlled by the control signals.  The source
      *  can either be the result of the ALU operation or
      *  a value read from memory.
      */

     private void writeBack() {
        int temp;
        if (control[2] == 1)
            temp = memOut;
        else
            temp = aluOut;
         if (control[3] == 1)
            if (control[0] == 0)
                regs[ir20_16] = temp;
            else
                regs[ir15_11] = temp;
      }

     /** Read the registers into readData1 and readData2
      */
     private void readRegs() {
         readData1 = regs[ir25_21];
         readData2 = regs[ir20_16];
     }
}