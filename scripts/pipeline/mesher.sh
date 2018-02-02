cd pwd

#frontend
mkdir build_image_frontend
cp ./front-end/Dockerfile ./build_image_frontend
cp ./front-end/package.json ./build_image_frontend
cp ./front-end/yarn.lock ./build_image_frontend
cd build_image_frontend
docker build -t frontend:autobuild .
docker tag frontend:autobuild registry.cn-north-1.huaweicloud.com/hwcse/sockshop-frontend:latest
