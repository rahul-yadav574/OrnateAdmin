package in.evolve.ornateadmin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.evolve.ornateadmin.Models.GuestHouseBookInfo;
import in.evolve.ornateadmin.Models.PgVisitInfo;
import in.evolve.ornateadmin.R;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class GhBookAdapter extends RecyclerView.Adapter<GhBookAdapter.GhBookViewHolder> {
    private Context context;
    private List<GuestHouseBookInfo> list;
    public GhBookAdapter(Context context, List<GuestHouseBookInfo> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public GhBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GhBookViewHolder(LayoutInflater.from(context).inflate(R.layout.guest_house_book_custom_single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(GhBookViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.phoneNumber.setText(list.get(position).getPhoneNumber());
        holder.noOfPeople.setText(list.get(position).getNoOfPeople());
        holder.date.setText(list.get(position).getDate());
        holder.noOfRooms.setText(list.get(position).getNoOfRooms());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GhBookViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView phoneNumber;
        TextView noOfPeople;
        TextView date;
        TextView noOfRooms;
        public GhBookViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.gh_book_name);
            email= (TextView) itemView.findViewById(R.id.gh_book_email);
            phoneNumber= (TextView) itemView.findViewById(R.id.gh_book_number);
            noOfPeople= (TextView) itemView.findViewById(R.id.gh_book_no_of_people);
            date= (TextView) itemView.findViewById(R.id.gh_book_date);
            noOfRooms= (TextView) itemView.findViewById(R.id.gh_book_no_of_rooms);
        }
    }

    public void changeList(List<GuestHouseBookInfo> list){
        this.list = list ;
        this.notifyDataSetChanged();
    }
}
