package logic;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import object.Neighbour;
import object.Path;
import object.BestColor;
import object.BestMove;
import object.Cell;
import object.Matrix;

import java.io.File;
import java.util.Vector;

public class DLVSolver {


	private static Handler handler;

	private InputProgram facts;

	private InputProgram encoding;

	private Matrix matrix;

	public DLVSolver(Matrix matrix) {
		DesktopService service = new DLV2DesktopService("lib"+File.separator+"dlv2.exe");
		handler = new DesktopHandler(service);

		try {
			ASPMapper.getInstance().registerClass(Cell.class);
			ASPMapper.getInstance().registerClass(Neighbour.class);
			ASPMapper.getInstance().registerClass(Path.class);
			ASPMapper.getInstance().registerClass(BestColor.class);
			ASPMapper.getInstance().registerClass(BestMove.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		encoding = new ASPInputProgram();
		encoding.addFilesPath("encodings" + File.separator + "twoDots.txt");

		this.matrix=matrix;
		initFacts();
	}

	public void initFacts() {
		facts = new ASPInputProgram();
		handler.addProgram(encoding);
		for (int i = 0; i < matrix.getDim(); i++) {
			for (int j = 0; j < matrix.getDim(); j++) {
				try {
					facts.addObjectInput(matrix.getElement(i, j));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		handler.addProgram(facts);
	}

	public void refreshProgramFact() {
		handler.removeAll();
		initFacts();
	}

	/*
	 * Il metodo controlla se il vector<Cell> contiene celle valide, attraverso gli atomi di Neighbour
	 */
	public boolean checkSelected(Vector<Cell> selected) {

		boolean ok=true;
		Output o = handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		//System.out.println(o.getOutput());
		if(selected.size()<2) {
			return false;
		}
		for(AnswerSet a : answers.getAnswersets()){

			//System.out.println(a.toString());
			try {
				for(int i=0; i<selected.size()-1;i++) {
					boolean check=false;
					for(Object obj : a.getAtoms()){
						if(obj instanceof BestMove)
							System.out.println(obj.toString());
						if(!(obj instanceof Neighbour)) continue;

						Neighbour tmp = (Neighbour) obj;
						if((tmp.getCol1()==selected.get(i).getCol() && tmp.getRow1()==selected.get(i).getRow()) && (tmp.getCol2()==selected.get(i+1).getCol() && tmp.getRow2()==selected.get(i+1).getRow()) ) {
							check=true;
						}


					}
					if(!check) {
						ok=false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

		}

		return ok;
	}

	/*
	 * Il seguente metodo serve per trovare una hint, attraverso gli atomi, torna un intero che rappresenta un colore.
	 */
	public int bestMove() {
		int c=0;
		Output o = handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		//System.out.println(o.getOutput());
		int best = -1;
		for(AnswerSet a : answers.getAnswersets()){
			try {
				for(Object obj : a.getAtoms()){
					if(!(obj instanceof BestMove)) continue;
					if(c==0) {
						BestMove tmp = (BestMove) obj;
						best=tmp.getColor();
						c++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return best;
	}
	
	/*
	 * Il seguente metodo serve per verificare se esiste almeno una mossa valida.
	 */
	public boolean alone() {
		Output o = handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		
		for(AnswerSet a : answers.getAnswersets()){
			try {
				for(Object obj : a.getAtoms()){
					if(!(obj instanceof Neighbour)) continue;
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
