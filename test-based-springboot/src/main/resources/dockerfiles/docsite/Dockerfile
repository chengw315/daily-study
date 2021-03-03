FROM node:10-slim

RUN npm uninstall --global gulp
RUN npm install --global gulp@3.9.1
RUN npm i -g docsite --unsafe-perm=true
RUN cd /root/
EXPOSE 3000

RUN docsite init my-project
RUN cd my-project
CMD docsite start