package in.evolve.ornateadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.evolve.ornateadmin.Models.PgRequest;
import in.evolve.ornateadmin.R;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class PgRequestAdapter extends RecyclerView.Adapter<PgRequestAdapter.RequestListViewHolder> {

    private Context context;
    private List<PgRequest> list;
    public PgRequestAdapter(Context context, List<PgRequest> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public RequestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestListViewHolder(LayoutInflater.from(context).inflate(R.layout.pg_list_request_custom_single_item,parent,false),viewType);
    }

    @Override
    public void onBindViewHolder(RequestListViewHolder holder, int position) {
       holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.type.setText(list.get(position).getType());
        holder.noOfRooms.setText(list.get(position).getNoOfRooms());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RequestListViewHolder extends RecyclerView.ViewHolder {
     TextView name;
        TextView email;
        TextView type;
        TextView noOfRooms;
        TextView address;
        public RequestListViewHolder(View itemView, int viewType) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.pg_request_name);
            email= (TextView) itemView.findViewById(R.id.pg_request_email);
            type= (TextView) itemView.findViewById(R.id.pg_request_type);
            noOfRooms= (TextView) itemView.findViewById(R.id.pg_request_no_of_rooms);
            address= (TextView) itemView.findViewById(R.id.pg_request_address);
        }
    }

    public void changeList(List<PgRequest> list){
        this.list = list;
        this.notifyDataSetChanged();
    }
}
