package in.evolve.ornateadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.evolve.ornateadmin.Models.PgBookInfo;
import in.evolve.ornateadmin.Models.PgVisitInfo;
import in.evolve.ornateadmin.R;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class PgBookAdapter extends RecyclerView.Adapter<PgBookAdapter.PgBookViewHolder> {

    private Context context;
    private List<PgBookInfo> list;
    public PgBookAdapter(Context context, List<PgBookInfo> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public PgBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PgBookViewHolder(LayoutInflater.from(context).inflate(R.layout.pg_book_custom_single_item,parent,false),viewType);
    }

    @Override
    public void onBindViewHolder(PgBookViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.phoneNumber.setText(list.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PgBookViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView phoneNumber;

        public PgBookViewHolder(View itemView, int viewType) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.pg_visitor_name);
            email= (TextView) itemView.findViewById(R.id.pg_visitor_email);
            phoneNumber= (TextView) itemView.findViewById(R.id.pg_visitor_number);
        }
    }
}
