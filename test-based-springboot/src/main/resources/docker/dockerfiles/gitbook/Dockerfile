FROM node:6-slim

ARG VERSION=3.2.1

LABEL version=$VERSION

RUN npm install --global gitbook-cli &&\
	gitbook fetch ${VERSION} &&\
	npm cache clear &&\
	rm -rf /tmp/*

WORKDIR /srv/gitbook

EXPOSE 4000 35729

CMD /usr/local/bin/gitbook install &&\
    /usr/local/bin/gitbook init &&\
    /usr/local/bin/gitbook serve