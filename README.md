# Instamojo Android SDK
Instamojo Android SDK

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
