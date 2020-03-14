FROM minidocks/base:build AS abcm2ps

RUN apk add pango-dev

ARG abcm2ps_version=8.14.6

RUN wget -O /tmp/abcm2ps.tar.gz "https://github.com/leesavide/abcm2ps/archive/v${abcm2ps_version}.tar.gz" \
    && tar -xvzf /tmp/abcm2ps.tar.gz -C /tmp && cd /tmp/abcm2ps* \
    && mkdir -p /tmp/build && ./configure && make DESTDIR=/tmp/build install

FROM minidocks/node AS latest
LABEL maintainer="Harald Wellmann <harald.wellmann@gmx.de>"

COPY --from=abcm2ps /tmp/build /

RUN apk add pango ghostscript openjdk8-jre && clean

COPY target/lib/* /deployments/lib/
COPY target/*-runner.jar /deployments/app.jar
COPY /scripts/convert.sh /deployments/convert.sh

EXPOSE 8080

CMD [ "java", "-jar", "/deployments/app.jar" ]
