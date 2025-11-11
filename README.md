AR cloud is a Banuba service that helps developers decrease the download size of their apps that run 
[face filters](https://www.banuba.com/facear-sdk/face-filters). 
Instead of bundling them with the application, software engineers can store effects in the cloud and allow users 
to download them as needed. Given that a single 3D mask takes about 2.5 MB, this option helps attract more users and 
make their experience more enjoyable. 

![image](image.gif)

You can license hundreds of AR face masks in the 
[Banuba Asset Store](http://assetstore.banuba.net) or create your own 
in any 3D software and make them compatible with Banuba in the 
[online studio](https://studio.banuba.com/).

  
# Getting Started

1. Get the latest Banuba SDK client token. Please fill in our form on [form on banuba.com](https://www.banuba.com/face-filters-sdk) website, or contact us via [info@banuba.com](mailto:info@banuba.com).
2. Copy and Paste your client token into appropriate section of [`BANUBA_CLIENT_TOKEN`](client_token/com/banuba/sdk/example/common/BanubaClientToken.kt#L7)
3. Open the project in Android Studio and run the necessary target using the usual steps.

# AR Cloud

 1. Get the latest BanubaARCloud archive for Android and AR cloud URL. Please fill in our form on [form on banuba.com](https://www.banuba.com/face-filters-sdk) website, or contact us via [info@banuba.com](mailto:info@banuba.com). The latest version of library can be found here: [BanubaARCloud](https://github.com/Banuba/banuba-ar/packages/665586).
 2. Copy received `aar` libraries into `arcloud-android-kotlin/libs/` directory.
 3. Copy and Paste your AR cloud URL into appropriate section of [`BANUBA_AR_CLOUD_URL`](client_token/com/banuba/sdk/example/common/BanubaClientToken.kt#L8) or use predefined Demo bucket

# Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
