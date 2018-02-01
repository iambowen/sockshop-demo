search='<servicecomb.version>0.2.0'
search1='<huaweicloud.version>2.1.11'
replace='<servicecomb.version>0.5.0'
replace1='<huaweicloud.version>2.2.31'
cd pwd
for file in `find -maxdepth 1 -name 'pom.xml'`; do
  grep "$search" $file &> /dev/null
  if [ $? -ne 0 ]; then
    echo "Search string not found in $file!"
  else
    sed -i "s/$search/$replace/" $file
    sed -i "s/$search1/$replace1/" $file
  fi  
done

mvn clean install -Phuaweicloud -DskipTests
chmod +x .

#carts
mkdir build_image_carts
cp ./carts/target/carts.jar ./build_image_carts
cp ./makedocker/carts/Dockerfile ./build_image_carts
cp ./makedocker/carts/carts.sh ./build_image_carts
cd build_image_carts
sudo docker build -t carts:autobuild .
sudo docker tag carts:autobuild registry.cn-north-1.huaweicloud.com/bigheadbird/sockshop-carts:latest
cd ..

#orders
mkdir build_image_orders
cp ./orders/target/orders.jar ./build_image_orders
cp ./makedocker/orders/Dockerfile ./build_image_orders
cp ./makedocker/orders/orders.sh ./build_image_orders
cd build_image_orders
sudo docker build -t orders:autobuild .
sudo docker tag orders:autobuild registry.cn-north-1.huaweicloud.com/bigheadbird/sockshop-orders:latest
cd ..

#shipping
mkdir build_image_shipping
cp ./shipping/target/shipping.jar ./build_image_shipping
cp ./makedocker/shipping/Dockerfile ./build_image_shipping
cp ./makedocker/shipping/shipping.sh ./build_image_shipping
cd build_image_shipping
sudo docker build -t shipping:autobuild .
sudo docker tag shipping:autobuild registry.cn-north-1.huaweicloud.com/bigheadbird/sockshop-shipping:latest
cd ..
