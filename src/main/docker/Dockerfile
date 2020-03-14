FROM minidocks/base:build AS abcm2ps

RUN apk add pango-dev

ARG abc2prt_version=1.0.2

RUN wget -O /tmp/abc2prt.tar.gz "https://sourceforge.net/projects/abcplus/files/abc2prt/abc2prt-${abc2prt_version}.tar.gz" \
    && tar -xvzf /tmp/abc2prt.tar.gz -C /tmp && cd /tmp/abc2prt* \
    && mkdir -p /tmp/build && make && mv abc2prt /tmp/build

ARG abcpp_version=1.4.5

RUN wget -O /tmp/abcpp.tar.gz "https://sourceforge.net/projects/abcplus/files/abcpp/abcpp-${abcpp_version}.tar.gz" \
    && tar -xvzf /tmp/abcpp.tar.gz -C /tmp && cd /tmp/abcpp* \
    && mkdir -p /tmp/build && make && mv abcpp /tmp/build

ARG abcm2ps_version=8.14.6

RUN wget -O /tmp/abcm2ps.tar.gz "https://github.com/leesavide/abcm2ps/archive/v${abcm2ps_version}.tar.gz" \
    && tar -xvzf /tmp/abcm2ps.tar.gz -C /tmp && cd /tmp/abcm2ps* \
    && mkdir -p /tmp/build && ./configure && make DESTDIR=/tmp/build install

ARG abc2midi_version=2020.01.22

RUN wget -O /tmp/abc2midi.zip "https://ifdo.ca/~seymour/runabc/abcMIDI-${abc2midi_version}.zip" \
    && unzip /tmp/abc2midi.zip -d /tmp && cd /tmp/abcmidi* \
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
