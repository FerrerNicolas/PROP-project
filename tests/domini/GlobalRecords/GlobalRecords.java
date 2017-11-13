package domini.GlobalRecords;

import domini.Tuple.*;
import domini.Player.*;

import java.util.ArrayList;
import java.util.Random;

public class GlobalRecords {
	
	ArrayList<Tuple> globalRecords = new ArrayList<Tuple>(6);
	
	public void update(Player p) {
		
			for(int i=0;i<globalRecords.size();i++) {
			if(globalRecords.get(i).getPlayerName().equals(p.getPlayerName())){
			Tuple newRecord = new Tuple(p.getPlayerName(),Random.class.);
			System.out.println("New tuple:" + newRecord);
			globalRecords.add(newRecord);
			sort(globalRecords);
		}
	}
	}
	/*Function to sort arrayList using insertion sort*/
    void sort(ArrayList<Tuple> gr)
    {
        int n = gr.size();
        for (int i=1; i<n; ++i)
        {
            Tuple key = gr.get(i);
            int j = i-1;
 
            /* Move elements of ranking[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && gr.get(j).getValue() > key.getValue())
            {
                gr.set(i, gr.get(j));
                j = j-1;
            }
            gr.set(j+1, key);
        }
    }
 
	public ArrayList<Tuple> getGlobalRecords(){
		return globalRecords;
				}
}