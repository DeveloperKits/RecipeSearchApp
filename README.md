# Culinary Quest
   Speed Bazar is a grocery B2B and B2C business platform apps.
   
#### Some information Speed Bazar:
   Speed Bazar is the fastest growing online grocery shop in Bangladesh, which is committed to delivering quality products for the individuals, families as well as for the corporate customers in the easiest way for your daily, weekly, and monthly grocery Bazar and for your Grocery Shopping through Online. Speed Bazar delivers all kind of grocery items such as cooking items, beverage, bread & bakery, snacks, milk & dairy, beauty and body care, baby care, health care, home & cleaning items, stationery and many more daily needs to customers' doorstep as per their requirements by ensuring quality, faster and secure delivery [Here, Faster Means - just within I hour delivery] with reasonable prices. Customers enjoy various discounts and offer while shopping in Speed Bazar throughout the year.
   We are always ready to manage customers’ hassle regarding daily, weekly, and monthly grocery Bazar for Online Grocery Shopping with our own expertise to save your time, energy, and money. Speed Bazar is a fast and smart shop at your door. You can buy any grocery items from our online grocery shop [Speed Bazar] and get the fastest as well as the best online grocery delivery service experience on your Online Grocery shopping. Just take a breath and feel free to contact us. Customer satisfaction is our first priority.
   We are happy to help you.
   
   #### Usage language, database & tools:
   * Android Studio
   * Java
   * Firebase real time & storage
   * Material UI
   
   ##### Speed Bazar Logo:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105648-c6b40700-52d9-11eb-8873-d50b36603784.jpg" width="300" height="300" />
   </p>
   
   ##### Main Activity:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105622-9b311c80-52d9-11eb-9fa6-55e5baad5550.jpeg" width="300" height="600" />
   </p>
   
   ##### Product Details:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105669-e51a0280-52d9-11eb-905c-612d8c4dce7c.jpeg" width="300" height="600" />
   </p>
      
   ##### My Account/ Profile:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105684-07ac1b80-52da-11eb-848a-a2578b6d3d68.jpeg" width="300" height="600" />
   </p>
         
   ##### Live Message With Admin:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105692-185c9180-52da-11eb-8a7b-c4332f6f75c0.jpeg" width="300" height="600" />
   </p>
       
   ##### Show nearest Vendor or Distributor on Map:
   
   <p align="center" >
    <img src="https://user-images.githubusercontent.com/69858580/104105704-28747100-52da-11eb-9a23-0f11bb28480e.jpeg" width="300" height="600" />
   </p>   
   
   
### APK
  + key store path: E:\All programming File\speedbazar.jks
  + key store password: speedbazar
  + key alias: key0
  + key password: speedbazar
  
# UpdateUserData From Firebase
```javascript 
    // where need to change edit there and change

    private void updateUser() {

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userRef.child(snapshot.getKey())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Map<String, Object> postValues = new HashMap<String,Object>();
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        postValues.put(snapshot.getKey(),snapshot.getValue());
                                    }

                                    postValues.put("isApprove", "No");
                                    postValues.put("vPanelAccess", "No");
                                    userRef.child(snapshot.getKey()).updateChildren(postValues);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {}
                            });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
```
   
# Our Contributors

<p align="left" >
 <img src="https://user-images.githubusercontent.com/69858580/104106061-ffa1ab00-52dc-11eb-8377-a0c520db2fe3.jpg" width="200" height="220" />
  <br> Akash Das
  <br> Android Developer | UX/UI Designer <br> <br>
 <img src="https://user-images.githubusercontent.com/69858580/104820791-7b61a180-5861-11eb-89e9-078fc88ec5a8.PNG" width="200" height="220" />
  <br> Niloy Sarker
  <br> Android Developer
</p>   

  
