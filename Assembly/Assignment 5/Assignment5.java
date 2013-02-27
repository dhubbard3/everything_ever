import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

/*
 * Main class to create and run MIPS-like simulation
 * @author Dave Hubbard and Debra Calliss
 */
public class Assignment5 {

    /**
     * @param args not used
     */
    public static void main(String[] args) {
        Processor simulator = new Processor();
        simulator.go();
    }
}


/** Simulation of MIPS-like processor
 *
 * Input: Hexadecimal instructions in a text file
 * Output: Display of processor state in a text file
 */

class Processor {

    /** number of instructions in memory */
    private int instrCount;

    /** clock signal */
    private int clock;

    /** program counter is incremented on each fetch */
    private int pc;

    /** internal registers between phases */

    /* instruction fetched */
    private String if_idReg;

    /* breakdown for id_exReg
     *  [0] = RegDest
     *  [1] = ALUSrc
     *  [2] = MemToReg
     *  [3] = RegWrite
     *  [4] = MemRead
     *  [5] = MemWrite
     *  [6] = Branch
     *  [7] = ALUOp1
     *  [8] = ALUOp0
     *  [9] = read data 1
     * [10] = read data 2
     * [11] = sign ext
     * [12] = ir25_21 (rs)
     * [13] = ir20_16 (rt)
     * [14] = ir15_11 (rd)
     */
    private int[] id_exReg;

    /* breakdown for ex_memReg
     *  [0] = MemToReg
     *  [1] = RegWrite
     *  [2] = MemRead
     *  [3] = MemWrite
     *  [4] = Branch
     *  [5] = ALU result
     *  [6] = Read Data 2
     *  [7] = Register destination
     */
    private int[] ex_memReg;

    /* breakdown for mem_wbReg;
     *  [0] = MemToReg
     *  [1] = RegWrite
     *  [2] = Memory data
     *  [3] = ALU result
     *  [4] = Register destination
     */
    private int[] mem_wbReg;

    /** 32 address and data registers */
    private int[] regs;

    /** data memory */
    private int[] dataMem;

    /** instruction memory */
    private String[] instrMem;

    /** maximum memory size */
    private final int MAXMEM = 20;

   Processor() {
        instrCount = 0;
        clock = 0;
        pc = 0;
        regs = new int[32];
        dataMem = new int[MAXMEM];
        instrMem = new String[MAXMEM];
        if_idReg = "00000000";
        id_exReg = new int[15];
        ex_memReg = new int[8];
        mem_wbReg = new int[5];
        String infile = JOptionPane.showInputDialog("Enter input file name");
        try {
            File file = new File(infile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext() && instrCount < MAXMEM) {
                instrMem[instrCount] = scanner.next();
                instrCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /** simulation of processor
     *  Process will continuously fetch and execute code until all instructions are
     *  completed.
     */
    public void go() {
        try {
            String outfile = JOptionPane.showInputDialog("Enter output file name");
            PrintStream output = new PrintStream(new FileOutputStream(outfile));
            printState(output);
            do {
                clock++;
                writeBack();
                memory();
                execution();
                instrDecode();
                instrFetch();
                printState(output);
            } while (clock < instrCount + 4);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** print the state of the processor including all internal registers
     *
     * @param outfile text file for output
     */
    private void printState(PrintStream out) {
        int index;
        out.println("Clock = " + clock + " Instr Count = " +
                instrCount);
        out.println("IF/ID " + if_idReg);
        out.print("ID/EX ");
        for (index = 0; index < 14; index++) {
            out.print(id_exReg[index] + "/");
        }
        out.println(id_exReg[14]);
        out.print("EX/MEM ");
        for (index = 0; index < 7; index++) {
            out.print(ex_memReg[index] + "/");
        }
        out.println(ex_memReg[7]);
        out.print("MEM/WB ");
        for (index = 0; index < 4; index++) {
            out.print(mem_wbReg[index] + "/");
        }
        out.println(mem_wbReg[4]);

        for (index = 0; index < 32; index++) {
            out.print("R[" + index + "]" + regs[index]);
            out.print("   ");
            if (index % 8 == 7) {
                out.println("");
            }
        }
        for (index = 0; index < MAXMEM; index++) {
            out.print("M[" + index + "]" + dataMem[index]);
            out.print("    ");
            if (index % 8 == 7) {
                out.println("");
            }
        }

        for (index = 1; index <= 3; index++) {
            out.println("");
        }
    }

    /** read an instruction from memory and place it into the if/id register
     *  and then increment the pc by 1.
     */
    private void instrFetch() {
        if (pc < instrCount) {
            if_idReg = instrMem[pc];
            pc++;
        } else {
            if_idReg = "00000000";
        }
    }

   /** decode the instruction and set the fields in the id/mem register.
   */

    private void instrDecode() {
        if (if_idReg.equals("00000000")) {
            for (int loc = 0; loc < 15; loc++) {
                id_exReg[loc] = 0;
            }
        } else {

			for (int loc = 0; loc < 15; loc++){ //decode from assignment 4
                id_exReg[loc] = 0;
			}

		    int [] nums = new int [8];
		    for (int index = 0; index < 8; index++) {
		         char ch = if_idReg.charAt(index);
		         if (ch >= '0' && ch <= '9')
		             nums[index] = ch - '0';
		         else
		             nums[index] = ch - 'A' + 10;
		         }

		    int type = nums[0] * 4 + nums[1] / 4;
		    id_exReg[12] = nums[1] % 4 * 8 + nums[2] / 2; //rs
		    id_exReg[13] = nums[2] % 2 * 16 + nums[3];    //rt
		    id_exReg[14] = nums[4] * 2 + nums[5] / 8;     //rd
		    int func = nums[6] % 4 * 16 + nums[7];
		    int imdt = nums[4];
		    for (int pos = 5; pos <= 7; pos++)
		         id_exReg[11] = imdt * 16 + nums[pos];
		    if (nums[4] >= 8)
             	 id_exReg[11] -= 256;     // convert to negative

			id_exReg[9] = regs[id_exReg[12]]; //readData 1
			id_exReg[10] = regs[id_exReg[13]]; //readData2

            switch(type){ // type indicates opcode
				   case 0: //add
				      	id_exReg[0] = 1;
				        id_exReg[3] = 1;
				        id_exReg[7] = 1;
				        break;
				   case 8: //addi
				        id_exReg[1] = 1;
				        id_exReg[3] = 1;
				        break;
				   case 43: // sw
				        id_exReg[1] = 1;
				        id_exReg[5] = 1;
				        break;
				   case 35: // lw
				        id_exReg[1] = 1;
				        id_exReg[2] = 1;
				        id_exReg[3] = 1;
				        id_exReg[4] = 1;
				        break;
         	}

        }
    }


    /** perform the operations required in the execute stage and set the
    *  fields in the ex/mem register.  Forwarding is not done.
    */

    private void execution() {
		ex_memReg[0] = id_exReg[2];
		ex_memReg[1] = id_exReg[3];
		ex_memReg[2] = id_exReg[4];		// Passing control values from previous regs.
		ex_memReg[3] = id_exReg[5];
		ex_memReg[4] = id_exReg[6];
		ex_memReg[6] = id_exReg[10];
		int rtype = id_exReg[0];		// indicates R type instruction
		int itype = id_exReg[3];		// indicates I type instruction
		int itype2 = id_exReg[4];
		int read1 = id_exReg[9];		// readData 1
		int ext = id_exReg[11];			// sign extended value
		int func = ext % 64;			// finds function code for alu
		if (func < 0 ){
			func = 0 - func;
		}

		if (rtype == 1){
			ex_memReg[7] = id_exReg[14];	//destination register to rd
			switch (func){
				case 32:	ex_memReg[5] = read1 + ex_memReg[6];	//add
							break;
				case 34:	ex_memReg[5] = read1 - ex_memReg[6];	//sub
							break;
				case 36:	ex_memReg[5] = read1 & ex_memReg[6];	//and
							break;
			}
		}

		if (rtype == 0){
			ex_memReg[7] = id_exReg[13];	//destination register to rt
			if (itype == 1 || itype2 == 1){
				ex_memReg[5] = read1 + ext;
			} else {
				ex_memReg[5] = read1 - ext;
			}
		}
    }


    /** Perform the memory read or write as determined by the control and set
     *  the appropriate fields in the mem/wb register.
     */

    private void memory() {
		mem_wbReg[0] = ex_memReg[0];
		mem_wbReg[1] = ex_memReg[1];
		mem_wbReg[3] = ex_memReg[5];
		mem_wbReg[4] = ex_memReg[7];
		int sdata = ex_memReg[6];		// holds store value
		int store = ex_memReg[3];		// determines store word instr.

		if (store == 1){
			dataMem[mem_wbReg[3]] = sdata;
		}
     }

    /** Write to the destination register as determined by the contents of the mem/wb
     *  register.
     */

    private void writeBack() {
		int dest = mem_wbReg[4];	//sets destination
		int load = mem_wbReg[3];	//determines if memory was loaded
		if (mem_wbReg[0]== 1){
			regs[dest] = dataMem[load]; //lw
		} else {
			regs[dest] = load; // r types
		}
    }
}

