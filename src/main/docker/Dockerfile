FROM minidocks/base:build AS abcm2ps

RUN apk add pango-dev

ARG abcm2ps_version=8.14.6

RUN wget -O /tmp/abcm2ps.tar.gz "https://github.com/leesavide/abcm2ps/archive/v${abcm2ps_version}.tar.gz" \
    && tar -xvzf /tmp/abcm2ps.tar.gz -C /tmp && cd /tmp/abcm2ps* \
    && mkdir -p /tmp/build && ./configure && make DESTDIR=/tmp/build install

FROM adoptopenjdk/openjdk8:jdk8u242-b08-alpine-slim AS latest
LABEL maintainer="Harald Wellmann <harald.wellmann@gmx.de>"

COPY --from=abcm2ps /tmp/build /

RUN apk --no-cache add pango ghostscript

COPY target/lib/* /deployments/lib/
COPY target/*-runner.jar /deployments/app.jar
COPY /scripts/convert.sh /deployments/convert.sh

EXPOSE 8080

CMD [ "java", "-jar", "/deployments/app.jar" ]
