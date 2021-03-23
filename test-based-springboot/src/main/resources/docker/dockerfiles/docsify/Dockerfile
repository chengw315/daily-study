FROM node:14-slim

RUN npm i docsify-cli -g

EXPOSE 3000

WORKDIR /root/docs

VOLUME ["/root/docs/"]

#RUN sh -c '/bin/echo -e "y" | docsify init /root/docs/'
CMD docsify serve /root/docs/