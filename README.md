# Instamojo Android SDK
Instamojo Android SDK

### Setup helper library
Download the aar file [here](https://github.com/sidhantpanda/instamojo-android/blob/master/instamojo.aar?raw=true).
Include in your libs folder and add to gradle
```gradle
compile(name:'instamojo', ext:'aar')
```

### Add ION dependency in gradle
```gradle
compile 'com.koushikdutta.ion:ion:2.+'
```

### Setup keys
```java
Instamojo.setKeys(API_KEY, AUTH_TOKEN);
```

### Initialize payment info
```java
final PaymentData paymentData = new PaymentData("Purpose", AMOUNT);
```

### Start the payment activity
```java
Intent i = new Intent(YourActivity.this, PaymentActivity.class);
i.putExtra("paymentData", paymentData);
startActivityForResult(i, INSTA_PAYMENT_RETURN_CODE);
````

### Check payment status
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == INSTA_PAYMENT_RETURN_CODE && resultCode == RESULT_OK) {
      String status = data.getStringExtra("status");
      if (status.equals("Completed")) {
        // Success
      } else {
        // Error
      }
    }
}
```
### CAUTION!
This is not the official android SDK from Instamojo. This is just an example for the implementation. If you lose your money because of this, I am not responsible.
