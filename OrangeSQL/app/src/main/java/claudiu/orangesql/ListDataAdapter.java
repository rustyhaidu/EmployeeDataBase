package claudiu.orangesql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudiu.haidu on 7/24/2015.
 */
// TODO - CODE REVIEW - andrei | 9/7/15 - Please reformat all classes. It's hard to read the code this way. Every method has a different indentation. Whitespaces are missing or too many.. It's VERY chaotic.
public class ListDataAdapter extends ArrayAdapter{
    DataProvider  dataProvider;
   List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Object object){
        super.add(object);
        list.add(object);
    }
    public int getCount(){
        return list.size();
    }
    public Object getItem(int position)
    {
        return list.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutHandler layoutHandler;
        if (row ==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.rowlayout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.tvEmployeeName);
            layoutHandler.SURNAME = (TextView) row.findViewById(R.id.tvEmployeeSurname);
            layoutHandler.GENDER = (TextView) row.findViewById(R.id.tvEmployeeGender);
            layoutHandler.BIRTHDATE = (TextView) row.findViewById(R.id.tvEmployeeBirthdate);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.SURNAME.setText(dataProvider.getSurname());
        layoutHandler.GENDER.setText(dataProvider.getGender());
        layoutHandler.BIRTHDATE.setText(dataProvider.getBirthdate());
        return row;
    }
    static class LayoutHandler
    {

      // TODO - CODE REVIEW - andrei | 9/7/15 - non static-final members should have lower-case names with camelCasing.
        TextView NAME,SURNAME,GENDER,BIRTHDATE;
    }

}
