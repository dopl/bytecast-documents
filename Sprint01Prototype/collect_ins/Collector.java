import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Collector {

    public static void main(String[] args) {
        String file = "../objdump_a.out";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            List ls = new ArrayList<String>();
            while(true)
            {
                String line = bf.readLine();
                if(line == null)
                {
                    break;
                }
                ls.add(line);
            }
            
            /*for(int i=0;i<ls.size();i++)
            {
                System.out.println(ls.get(i));
            }
            */
            StringTokenizer st = null;
            List to = new ArrayList<String>();
            List f = new ArrayList<String>();
            for(int i=0;i<ls.size();i++)
            {
                String a = ls.get(i).toString();
                if(a.endsWith(":"));
                else
                {
                     st = new StringTokenizer(ls.get(i).toString(),"\t");
                     if(st.countTokens() > 2)
                     {
                     while(st.hasMoreTokens())
                        {
                            to.add(st.nextToken());
                        }
                        if(!to.isEmpty())
                        {
                            String last =  to.get(to.size()-1).toString();
                            
                            //if(last.endsWith(":"));
                            //else
                            //System.out.println(to.get(to.size()-1));
                            f.add((to.get(to.size()-1)));
                        }
                     }
                    //System.out.println(to.get(to.size()-1));   
                    to.clear();
                    }
                }
            Set s = new HashSet();
            for(int i=1;i<f.size();i++)
            {
                StringTokenizer st1 = new StringTokenizer(f.get(i).toString(), " ");
                //while(st1.hasMoreTokens())
                 //   System.out.println(st1.nextToken());
                s.add(st1.nextToken());
                //System.out.println(st1.nextToken());
                //st1.
                //while(st1.hasMoreTokens())
                //{
                 //   System.out.println(st1.nextToken());
                //}
                //System.out.println(ls.get(i));
            }
            
            
            
            System.out.println(s);
            List finalList;
            finalList = asSortedList(s);
            
            System.out.println(finalList);
            writeText(finalList);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Collector.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) 
    {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
    
  public static void writeText(List<String> list) 
  {
    PrintWriter writer = null;
     try {
       writer = new PrintWriter("instructions.txt");
       for(int i=0;i<list.size();i++)
        writer.println(list.get(i));
        
       //writer.println("hello world");
       writer.flush();
     } catch(Exception ex){
       //ignore
     } finally {
       if(writer != null){ 
         try {
           writer.close();
         } catch(Exception ex){
           //ignore
         }
       }
     }
    
  }
}
