# ----------------------------
# build from source
# ----------------------------
FROM node:18 AS build

WORKDIR /app

COPY package*.json .
RUN npm install

COPY . .
RUN npm run build

# ----------------------------
# run with nginx
# ----------------------------
FROM nginx

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d
COPY --from=build /app/dist/vincle-web /usr/share/nginx/html

EXPOSE 80

#docker build -t vincle-web:1.0 . -f docker/Dockerfile
#sudo docker run -p 80:80 -t vincle-web:1.0