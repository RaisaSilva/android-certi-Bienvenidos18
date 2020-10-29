package bo.com.bienvenido18.android.repository.fireBase.db;

public class FirebaseDbManager {


    /*public LiveData<List<Tramites>> observeTramites() {
        MutableLiveData<Base<List<Tramites>>> results = new MutableLiveData<>();
        String path = "tramites";
        db.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Convert to tramite
                GenericTypeIndicator<Map<String, Tramites>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Tramites>>() {
                };
                Map<String, Tramites> map = snapshot.getValue(genericTypeIndicator);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return results;
    }*/
}
