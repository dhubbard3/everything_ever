import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

/* Simulation of MIPS-like processor
 * Input: Hexadecimal instructions in a text file
 * Output: Display of processor state in a text file
 * Authors: Dave Hubbard and Debra Calliss
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
        String infile = JOptionPane.showInputDialog
		("Enter input file name");
        try {
             File file = new File(infile);
             Scanner scanner = new Scanner(file);
             int index = 0;
             while (scanner.hasNext() && index < MAXMEM) {
               instrMem[index] = scanner.next();
               index++;
             }
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

		 String set="";
		 switch (ir31_26){
			 case 0: 	set = "100100010"; //add
			 			break;
			 case 8:    set = "010100000"; //addi
			 			break;
			 case 35:	set = "011110000"; //lw
			 			break;
			 case 43:	set = "010001000"; //sw
			 			break;
			 default: 	break;
		 }

		 for(int i=0; i<9; i++){
			 control[i] = set.charAt(i) - '0';
		 }
     }

     /** based on the control signals and function code
      *  in ir5_0, set the control signals for the ALU
      */

     private void setALUControl() {

		 String aluCtl="";
		 if (control[7] == 1) {
			 switch (ir5_0){
				 case 32:	aluCtl = "0010"; //add
				 			break;
				 case 34: 	aluCtl = "0110"; //subtract
				 			break;
				 case 36:	aluCtl = "0000"; //and
				 			break;
			}
		} else {
			aluCtl = "0010"; //add
		}

		for(int i=0; i<4 ; i++){
			aluControl[i] = aluCtl.charAt(i) - '0';
		}
     }

     /** perform the ALU function based on the ALU control
      *  signals.  Input can be from with readData2 or from
      *  the sign extended ir15_0. */

     private void performALUOp() {

		 int op;
		 if (control[1] == 1) {
			 op = ir15_0;
		} else {
			op = readData2;
		}

		switch (aluControl[2]){
			case 0: 	aluOut = readData1 & op;
						break;
			case 1: 	if (aluControl[1] == 1){
							aluOut = readData1 - op;
						} else {
							aluOut = readData1 + op;
						}
						break;
		}
     }

     /** Perform the memory read or write as determined
      *  by the control signals.
      */

     private void accessMemory() {

		 if (control[4] == 1){
			 memOut = dataMem[aluOut];
		 }

		 if (control[5] == 1){
			 dataMem[aluOut] = readData2;
		 }
     }

     /** Write to the destination register as
      *  controlled by the control signals.  The source
      *  can either be the result of the ALU operation or
      *  a value read from memory.
      */

     private void writeBack() {

		 if (control[5] == 1){
			 regs[ir20_16] = memOut;
		} else {
			if (control[0] == 0){
				regs[ir20_16] = aluOut;
			} else {
				regs[ir15_11] = aluOut;
			}
		}

     }

     /** Read the registers into readData1 and readData2
      */
     private void readRegs() {
		 readData1 = regs[ir25_21];
		 readData2 = regs[ir20_16];
    }
}


/*
 * Main class to create and run MIPS-like simulation
 * @author Debra Calliss
 */
public class Assignment4 {

    /**
     * @param args not used
     */
    public static void main(String[] args) {
        Processor simulator = new Processor();
        simulator.go();
    }
}


