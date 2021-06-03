
<p align="center">
  <img src="https://i.imgur.com/o2qY3sq.jpg"/>
</p>

## Links

[@olafurpg](https://twitter.com/olafurpg) on Twitter

[@olafurpg](https://github.com/olafurpg) on GitHub

[geirsson.com](https://geirsson.com/)

[Tooling Talks Recording](https://youtu.be/85OfN0aRjDk)

## Transcript

Chris

I want to thank again everybody for being here. This is something I've really
wanted to do for for quite some time. I've played around with like different
formats of how I wanted to do this but ultimately it was always focused around
the same thing as like I wanted to be able to like have an informal conversation
with somebody in the like Scala community and sometimes outside of the Scala
community as well just about tooling, about things that we use every single day
and there's a couple of specific goals that I had in mind as well. One of them
was sort of a selfish goal that I just wanted to talk to people about tooling
that I respect and pick their brain about their work and what they do and I tend
to have tons of questions that I'm just like I wonder what this person thinks of
this or I wonder I wonder how why they did this or something. So part of it's
selfish the other part of it is that I feel like sometimes there's this
assumption that getting into tooling is pretty difficult or that it's
challenging because it's either there's like a lack of documentation out there
compared to other areas or dealing with things like compilers or lexers or
something are difficult so if you have to be like a pro to do it but I've
personally found the tooling community super welcoming and super helpful and
they've always been willing to point me in the right direction or shine a light
on on certain things that I was unaware of and I've just had a really really
great experience for getting involved in tooling. So I wanted to hopefully
contribute to that by doing these. Because of that the first guest that I have
here which I'm super happy to introduce is Olaf. It's fitting that he is my
first guest as well because he's been a great example of the goals that I sort
of mentioned. Not only has he just done like a ton of work on tooling of various
types like Scalafmt, Metals, Scalafix, Scalameta, and the list goes on and on
and on but kind of apart from all the great work that he's done he's also been,
he was actually the first person I reached out to when I got involved in open
source the first person that sort of like introduced me to the tooling world and
I've had nothing but kind responses that have been super helpful. His answers
are always really insightful into the ecosystem. So I'm really really happy to
welcome Olaf as the first guest to Tooling Talks. So thank you for being here
Olaf, I'm really excited.

Olaf

Thank you for inviting me.

Chris

Yeah so after that short introduction do you also want to do just like a short
introduction of yourself and just maybe tell everybody who you are, where you
work, where you're from, where you're situated? I don't know like anything else
that you want to add about yourself.

Olaf

Sure, so but first of all thank you for organizing this. Yeah, it was kind of
exciting when you invited me I thought this was a would be a fun chat. Yeah, I'm
Olaf, and I live in Norway right now. I work at a company called Souregraph that
provides search for a lot of companies that buy a product called Sourcegraph
that allows you to search the code in your company. And before that I was
working at Twitter for a year and then before that I was at the Scala Center for
three years where I was doing all sorts of tooling related stuff for open source
Scala. I'd say I was doing that at twitter as well, it's kind of the same thing
at Souregraph as well. So I've been doing mostly just full-time open source
developer tooling for closing in on five years now. Yeah, excited to be here and
chat.

Chris

Why don't you also give a short introduction in how you got interested in
programming in the first place. I know that you, I think, believe went to
university in it and then started working at the Scala Center, but yeah maybe
give us a better insight into how you first got started in programming and yeah
go from there.

Olaf

Yeah, no I had never programmed, no one in my family programed. I didn't code
anything in high school. I went to, I got a scholarship to do a year in the US
in college that was not really to do the full program just one year, and I
studied history and political science. Then I knew I was going to go back to
Iceland where I'm from and wanted to do something proper and I did the computer
science program because I got a recommendation that the program there was good,
which it was, it was really really really good and I had a fantastic three years
at the university at the Reykjavik University in Iceland. I was kind of lucky
with the timing of a specific program that was starting and so that's, then I
went to Switzerland for my master's where I got involved with Scala and more
compiler related work. I didn't do any compiler courses at all during my
bachelors.

Chris

So okay, looking back at some of... I actually, I'm gonna take a pause because I
do see that the chat is saying that the frame rate is really low which I see
it's good from OBS. I don't know exactly what's going on but if something
freezes chat feel free to just let us know and we'll try to to figure something
out. All this is, all this is new. But jumping back to what you're saying, I
like while you're at the Scala Center I think it's probably the first time that
you started working on stuff that maybe other people were aware of? If I'm not,
and I looked, like I stalked through your git history on GitHub of some of your
earliest things and it seems that if I'm not misunderstanding it, Scalafmt in
late 2015 was sort of the first large project that you started working on?

Olaf

That's true.

Chris

Yeah, go ahead talk a little bit about that.

Olaf

Yeah, so I was an intern at the time at Google in Zurich, Switzerland and then
the project I was working on was kind of like the dependabot but for Google's
internal monorepo. And this bot was enabling code formatting for a bunch of
directories and I got used to having code for writing on file save. For python
it was a really bad formatter at the time it kind of broke half of the time. The
project was in python so I thought and I enjoyed Scala so I thought that would
be a cool idea to maybe build a code formatter for Scala. So I think I started
right then and end of 2015 at the same time when I approached Eugene Burmako who
was a PhD student at EPFL who was the starter in Scalameta and he created the
Scalamacros and a lot of other really cool stuff in Scala. So I ended up doing
my master's thesis project with him, which ended up being Scalafmt.  So before
that I had mostly done, I've done some work where it's like building a payroll
system in Iceland. I had done some calendar like flight booking widgets in
react.js and stuff. So I hadn't really done any open source work before before
that.

Chris

I wasn't doing Scala at this time but I'm also curious because and we've talked
about this a little bit in the past I believe at that time Scalariform I believe
also existed? Which was another code formatter. What made you come out with
another Scala, for like another formatter when one already existed? Was there
either things that it didn't have, that one didn't have that you were adding or
was it like everything from the internals was different?

Olaf

My kind of harsh view on it.  I don't think everyone would agree, but like for
me the big one is that Scalariform didn't have a maximum column setting, so it
means that it would in my opinion it's more of an indenter because it will not
take, it doesn't really impose any style whatsoever on how you format stuff it
will choose what indentation level you will use at every line and that's it.

Chris

And that's it? Just indentation level?

Olaf

Yeah, and go fmt is exactly the same, and then you hear these Go people boast
about like how fantastic go format is except like there's no settings right?
Except you have to like configure every single line whether you're going to do a
line break or not. So I wanted something that was similar to the Clang formatter
that I was using for C++ at Google and the python one that I was using, which
would take like way more control over how we format it with line break, line
wrap and kind of remove all personal flourishes that you would try and add to
the code. So that was my big motivation and for me. Like everyone kind of threw
Scalafmt and Scalariform into the same category, but in my head they're not the
same category and one is an indentor in my opinion and that's really useful for
people who want that, but that's not what I wanted.

Chris

Okay right I feel like, well actually one other thing I wanted to ask about is
that I've also think heard you referenced that it was inspired by prettier as
well. Is that possibly true?

Olaf

That's not true because prettier didn't exist at the time no.

Chris

Okay So prettier is based on Scalafmt?

Olaf

I'm not gonna say that either. I'd say the true original one is clang format
which is just the phenomenal code formatter for c++. I tried to like copy some
of the implementation approaches, which I was really bad at it so I'd like it
has an exponential core loop and blows super quickly as some people may be
familiar with. And and that's all my fault and it's totally possible to use the
same approach and do well but, I did not have the chops to do that.

Chris

Was there like one thing particular about it that was really solid that you say
like you tried to copy things? Like was there a feature that it had or?

Olaf

No no, I think just ClangFormat is ridiculously fast and it does line wrapping
and has a good like, it provides good settings like if you just want to use the
Google code style you use the Google code style. If you use the Mozilla one you
use that one yeah, I think they got a lot of those things right that Scalafmt
just has never gotten right or never managed to get right.

Chris

So you mentioned it really briefly and then we moved on and I actually
want to spend some time on it. You mentioned Scalameta and then you
like talked about some other stuff but I feel like a lot of the tools that you
worked on are heavily reliant on that.

Olaf

They are and there's a big red thread around the whole, all of these projects
and yeah so Eugene Bermako was a PhD student at EPFL, he was the author of the
Scala 2 macro system Scala reflect and author of the paradise macro annotation
plugin and he was a fantastic advisor for me to work with. When I approached him
he was so enthusiastic and at the time he had learned a lot of the lessons or
like the caveats of what he had built and he wanted to do like a fresh start,
which was Scalameta. So he wanted from the ground up to build a new meta
programming toolkit that would address all meta programming needs in Scala down
from like tokenizing parsing like macro expansion and everything. So when I
approached him I said I wanted to do a code formatter, he said oh that perfectly
fits within the umbrella of Scalameta and and so that's Scalameta. And that's
the way it's mostly used today there is a tokenizer and there's a parser and it
has syntax trees and then it has quasi quotes that make it very easy to
construct. But I personally don't think I use quasi quotes in any of the
projects that I've done so for me it's mostly just parsing strings into trees.

Chris

You didn't mention semanticdb there either. Was that a later addition to
Scalameta?

Olaf

It was a later addition, yeah. That was something that came out right at the
end. So Eugene joined twitter and he was working from there and then I joined
the Scala Center.  That was not an obvious thing that happened but that's
another story maybe, but at the time I ended up, I was working at the Scala
Center and the goal was, well Scalafmt was successful or people seemed to be
using it and the idea was that we could use the same approach and like do not
just formatting but actual refactoring and the key motivator here was to unblock
Scala 3 migrations and then what I say we added semanticdb. It was kind of the
solution to enrich these parsers from Scala meta with semantic information which
would be something like the symbol of something if you rename. Like flatmap you
don't want to rename all flatmaps, you want to rename the ones that are on the
collections only. Not like the ones that are IO task if that makes sense.

Chris

*Left Off*
NOTE: if anyone wants to help improve this, please do, but ping me so we don't
work on the same stuff. I will slowly continue down this as I get time.

okay so but that came a few years later and if
i understand it correct which i think i got it right last time i talked about it
but your workout source graph when you're working on java lsif also has to do
with semantic db is right correct yeah yeah yeah so i mean even in my job right
now i'm working with uh i'm on the code intel team at sourcecraft and we're
adding go to definition find references for uh 40 plus programming languages and
and currently the what i've been working on since january is mostly java and uh
we did that by implementing a semantic the compiler plugin for java so that's
just completely complementing building on top of all of the prior work that's
that i've been doing since 2016 basically hypothetically could it ever be
expanded to include other languages not on the jvm as well you can for sure and
and a colleague of mine that sourcecraft is experimenting but this is really
super super nut but like we might want to try and use the same approach for
kotlin um so but uh you could do it for other languages too yeah um but at that
point you might as well like just the general approach of emitting some part of
the files that have uh semantic information is is good enough um okay is there
like one thing i've always wondered about scala meta is well i've wondered a lot
of things about scala made up but one of the things i often hear about is now's
your time to ask questions this is actually just a time for me to ask all the
questions like i haven't been active on getter so you just talked on the call
hey do you want to do an interview yeah so like one thing i've been meaning to
ask you is like is it like because the the possibilities are probably like
endless of tooling that you could do with with simply almost like trees and
semantic db almost so like is there anything that you've ever like thought about
in your head of like oh this would be a killer thing to make with skalameda uh
that that just doesn't exist yet because either you don't have the time or you
just don't know how you do it like what's like the untapped area that you could
use galameta for it's always been code search for me so i'd say that the true if
you ask me like how did i get involved in tooling i i was doing this internship
and um but i got to use the google's internal code search which is the most
amazing developer tool i've ever used it still is and and like it was uh so i
approached eugene i actually told him hey i want to do a concert for scala and
how do i get like dog strings and one so that's what i'm working with right now
okay and i'd say it's uh i have one project in scholarly meta browse but nobody
uses it uh i think that was a really cool and it is a cool idea it's used by
allman the scala notebook and and it's basically a static site generator that
allows you to use the equivalent ui fps code to navigate online without having
compiled anything okay and i feel like that this is something that should be
included with scala docs or something like that and um so that's been untapped
in my opinion okay and i'm hoping that some of the work on source graph is going
to unlock that like we have some really exciting work in the pipeline to to
provide code navigation for published libraries on maven and we could do that
for the entire ecosystem and you could do like find references on cat's io task
or and then it would it would show you references in all of these the whole
ecosystem okay so like where would that like you say that that's like crazy
right because like what yeah we're working on it so like it's super exciting
like maybe the question that then like the next question that comes to mind is
like where would you do that from right because like that's that's a ton of
that's a ton of jars right like there's there's so many jars so like you need to
have some type of central place right that like are you actually downloading
those jars and then unpacking them and looking through or like like how does
that like talk a little bit more about like how that would actually work because
yeah for an online code search you end up having production servers you need
databases and and it's a completely different world than the developer tool like
local installation building a language server it's it's a completely different
game uh you can use different like you have different trade-offs in terms of um
what what uh how your what indexing technologies you want to use because if it's
read-only so but yeah but the sources you have been everyone published in maven
you can download the sources from maven you've got the class paths you can type
check the sources and so everything is there and it's actually i'd say indexing
code is super slow when you have to like start spt and it'll download a bunch of
spd plugins that have nothing to do with the actual source code you're trying to
navigate and then bin trace down and you can't index the repo because bintra is
done whereas the sources that are published in maven they're always available
they are ridiculously fast to download and they're super fast to type check as
well yeah you don't even have to admit bytecode you can stop as soon as you've
done a bit into the semantic to be so um i feel like that's a huge untapped
opportunity um like a very skeptical question would be like well that's the same
thing like text search on github right like when i when i type yeah whatever i
just did a whole bunch of references like obviously it's different right because
it's like compiled code that you know yeah you know is legit but like there's
similarities there right there's the similarities i have an analogy that i'm
using sometimes to explain like the difference and it's like imagine if you're
cooking you're in a kitchen you've got a rack of spices but none of them have
labels and you just kind of have to squint at like this looks red so i'm
guessing it's paprika you know we put in some and then it's cayenne and it's
like your dinner is super spicy that's how it would be to like be on to do text
search versus having the real deal like ide quality navigation when you talk
about like that type of search across a lot of different sources and maybe this
seems obvious to some people or to you even but like what what what is that most
useful for then for like an average developer like you mentioned like having io
and search for io or something is it simply to like see how these things are
being used or like yeah i mean i now i had to kind of i was doing some
typescript i was i'm writing some goal and and i'm the thing i'm needing is like
how do i find examples how to do this thing how do i use this api like not from
the actual docs like i would love to just find all of the actual real world
usages ideally sorted by you know promote the usages from um from popular
repositories or high quality repositories that make sense um okay and so an
average developer should care about it because they can learn so much better how
to code they will quickly find exactly the the usages that they're looking for
yeah um yeah i know or sometimes there's like three method overloads and like
well nobody uses the other two because they're unsafe or something but that's
easy to miss in your id even for new programmers i feel like this or like if
you're new to any language like i know for myself like i've been doing a lot of
lua lately and i have like sometimes no idea how to do something especially when
i'm dealing with like very specific apis i like will literally just think i
think i kind of want to do this and these two like these two parts of the thing
are probably right i'll just search for that across all of github and more than
likely yeah i'll find an example of how to a father do it and like i'd actually
do that way more than i used to so yeah i get the reason for that well you want
to learn like node.js i'm trying to fork a process to to run some stream but i
want to get like the streaming output or like whatever uh these are things that
you could find very effectively if you were just browsing using an id for your
for the global graph of code yeah yeah okay [Music] and there is one parallel
use case which is more enterprisey but it's like you you you're at a company you
have tons of repos and you want to rename a method and you can't use intellij
because there's like 10 repos and you only have one open i think that's uh
that's right no i have not probably not i don't know eclipse was really good for
you could have like a whole bunch of different projects all opened inside of
eclipse at one time and like renames would work across projects they wouldn't
work with metals or lsp like metals doesn't really support like multi-workspace
yeah intellij does that pretty well but i think there is uh quite a lot of
complications in setting up a local environment to do like cross-repo
refactorings do you think that's something i mean we can shift gears a little
bit into metals land if you want but yeah yeah that's like before i ask that
specific question too like i'll take a step back and uh arguably say that metals
what do you think is the most like well-known project you've worked on like are
you are you more proud of one of your projects and all the other ones that
you're like this is my baby like this is this is the thing i'm proud of or you
learn them all equally well varying degrees of love and affinity to my projects
i guess what's your favorite favorite so i think the most fun and rewarding
where i feel like things were done right was medals for sure and a lot of that
was that clicked was i think just the it was didn't feel like a solo project
okay and and from the start i was super lucky to have multiple people involved
and i i will do i will make a mistake if i start enumerating names because then
i'll forget someone but i mean we did have a chat that was like gabriel and we
had alexey who is uh not super active today but uh he lives in oslo so we still
hang out and play board games and and we just got met through medals basically
at the time and then we had uh eugene and we had vodka and then well you joined
and and there were there were more uh so it felt like compared to let's say
scala format that was like me alone doing all of the code getting it out there
and then having a lot of users with really you know active contributors
unfortunately that change for that only started like let's say a year two ago um
where we we have some really active contributors now to scholar format
fortunately it's huge yeah yeah and honestly that's that's one thing i had
written down that i wanted to like both comment on and ask if there was like any
like wisdom that you have on this but i feel like you've done a really really
good job at the majority or if not all of the projects that you've started or
have like worked pretty heavily on that you worked on them for quite some time
you did like tons of tons of work for like maybe a year two years and then you
somehow like were able to like smoothly like transition it to other people that
really wanted to take it over and then they kept it alive and kept going like i
i feel like that's rare like rarer than than we give it credit for because
there's a lot of projects out there that like were maintained for a couple years
that were not necessarily a passion project it could have been a project that
somebody used for a long time and then the person either moved on to something
different moved on to a different language and there wasn't necessarily people
there to like pass the torch to where i feel like you've done a really good job
of that has that been something intentional on your mind or has it sort of just
worked out that way it doesn't happen just randomly or actually it did happen
randomly it's california so i'm not even going to claim credit for that but i
made a massive omission earlier with metals which is burgerslab tomek uh has
advertised basically has been driving this project now for more than two years
and and he um and so just on that note on like keeping the project sustainable i
don't think it would have been sustainable without burgers labs financial
backing like they've been fully funding engineers to work on it yeah so in that
way i don't think that that was a typical scenario of your like standard open
source projects but it didn't happen either uh like in that particular case i
did hop on a plane go to poland uh with uh and then we were like pre-programming
for a week and and so so that was um showing how to run the tests you know it's
like oh that's fine that crash you just restart sbt and then you move on that
kind of stuff was critical i think to get that initial like hey i could and and
not especially then preparing a few issues i think where they were first i was
able to ship super high impact features in within a reasonable time uh at the
beginning and so in that case you know there's i i'd say there's no there are
some answers to your question which are consistent for all of the projects and
some of them are random okay so if you're looking for the like consistent
answers uh the two the biggest one is like have automated releases it's the
cheapest these are like tips and tricks on how to get more maintainers okay you
should absolutely have automated releases if you're publishing from your
computer you're just like and that was the norm in 2016. everyone did it that
way and i'd say that was a huge one yeah i recently like somewhat took over the
maintenance of a project recently and uh i didn't fully appreciate sptci release
as much as i did until i had to be like wait a second i need to push this out
locally and just wait for a while and then move it to staging and then and it
the process was like three to five times longer than it was of just pushing up a
tag and then forgetting about it so yeah yeah i didn't even know that but and
that's like that was the norm i think people forget it like now it's obvious
like mostly the community has adopted automated releases but that was completely
the norm to publish from your computer uh at the time and i'd say that it is one
key component that you need in your project if you're gonna uh get other
maintainers get on board like i always saw like when an active motivator
contributor would come to the project and then they would be able to push a tag
and there'd be a release like that was always the best moment especially if it
included a fix that they had contributed yeah um and no go ahead combined with
like kind of generously handing out maintainer rights to people who do show the
interest and ambition and motivation and they're uh yeah like to not hold on to
it too strictly uh is another component like it's it's i understand some
projects are have different uh i've seen a lot of projects that end up putting a
bunch of process on how it's done yeah and and i think that can sometimes be uh
unnecessary and but you also don't want to make it too easy where it's like
there's you you want to celebrate it when someone gets maintainer rights you
know you open up popularity and say we're really happy for these pr's like we're
happy to have them join the team but at the same time you know have a dm
dialogue with that person sending them a message saying hey i really appreciate
your work you know here's maintainerize so you could like push the tag and kind
of release whatever you want just so you know like there's no obligation
whatsoever to do any work or respond to any issues like you if life move up
moves on so even if you won't make any contribution from now on like that's
completely fine yeah and there's a lot of behind the scenes work to like that
like dms with people sending communicating stuff like that yeah um so it yeah it
doesn't happen completely by itself but i think there's also a combination of
luck and getting some of the basics right yeah yeah okay so let's like jump back
to metals too before we go back to too far into that direction but yeah so like
metals is also like a game changer i think in the community in some ways because
uh yeah in some ways it provided another alternative to other maybe more popular
editors um but also at the time there was another another project enzyme too and
like it was actually right when i wanted to start working on metals so sort of
right when i was starting scala and uh it was right at like the tail end of
enzyme so can you talk a little bit like how that ended and this started or like
why start metals when enzyme existed and that type of thing right at the time
there were multiple language servers so there was like enzyme was the original
one and it had its own protocol and then lsp came along which was a revolution
largely in the kind of id scene for the other ecosystems and then there were
efforts and there were uh language server client like server implementations
that used enzyme as the as the engine and and there were several of those and
then there was the the dotti language server um because previously dottie had
its own implementation of lsp as well so and i was working at the center on on
scala fix and i was kind of i think gabriele was the reason why the medals
started and it mostly started because he wanted to get scala fix diagnostics
into this code and i was like yeah that was we were dming and i said like yeah
it's you know we can totally do it you know it shouldn't be that hard uh i had
actively said no to any kind of heart i had any salutations on like why don't
you do id so natural like you've been doing scala formats fix like it's very
natural that you would do ideas and and i said i know that's way too hard
there's no chance i loved intellij and i still do intellij was working fine for
me i had no issues whatsoever and and i was like do you see how many people work
at jeff prince and they're really good at it and they've been building a moat
for 20 years uh forget it like we're not building an ide and the expectations on
latency like there were i'd say hundreds of technical problems that i just
hadn't even started building any wires in my head don't know how to solve
because you have incremental state incremental indexing super low latency
completions so i and it would never have started if i tried to solve all of
those at the same time either yeah so yeah but didn't really answer your
question with enzyme so at the time it started really just to get those scala
fix diagnostics and it was a hobby project this was not a skull assignment
project this was something we were just having fun in the evenings and at the
time there were tons of experiments i mean there were tons of different there
was a lot of proliferation of language servers and and experimentation at the
time later on um heather miller who was the director at the scala center
fortunately i kind of saw that i was clearly showing a lot of interest in this
side project of mine which wasn't my main assignment at the center and she asked
me if i wanted to work on that full-time and um which i jumped on and got the
green light on and then then kind of we were building so much from the grounds
up with semantic to be a different approach out to index stuff and we had very
very specific ideas of like how the integration with the build tool should be
the bsp which would be this parallel protocol to lsp yeah um and so we had i
feel like a lot of fresh ideas on like hey maybe we can get it right or get it
right not that nothing if some before it wasn't wrong it's just like this is how
we feel like would be a cool like a good solid way to do an idea and i think
even intellij benefits heavily i've heard lots of people who prefer to use
intellij with bsp um and and that's because yeah so so i mean i'm familiar with
like how intellij sort of has their own internal compilation model that they
they use and i know the bsp model but what what did the model that enzyme have
like if they weren't really using any type of bsp protocol and they didn't have
were they just directly using the presentation file or so i'm gonna if like if i
claim technical details on how to work i'm not gonna be called out for making
incorrect segments but like my understanding it is that it would uh you would
have a plug-in it would it's kind of like bloop you know it would have its own
plug-in it would emit some json or some other syntax config files uh and um and
then that would be it so if you wanted to update your class files you would have
to trigger build compile again in your build tool yeah and and then there would
there would be file watchers that would observe the new class directories or
class files but there was no way from the language server or enzyme itself to do
the equivalent of an spt compile which is what sp the dsp enables where you
could like via bsp you can go like the ide can control your build tool yeah but
uh i'm yeah that was my understanding at the time it's still in my understanding
but honestly is that completely factual to be true like maybe not i'm not yeah
i'm gonna put in a disclaimer on these i mean so i don't think people maybe
realize that but i feel like so there was like a two-year period that we just
talked about probably that like tons of work on skala beta was being done scala
formats was started metals was started scala fix was started the build server
protocol was maybe implemented and bloop was being worked on that was all like
in a two-ish three-year period wasn't it that those things three-year period
including mdoc and sptc release and matter browse as well and i still have faith
in mata bros i'm really excited about that yeah it's gonna be good uh eventually
thank you well i'll ask a couple questions about those but i still have a couple
more medals things too so like i feel like metals is probably the project of
yours that i'm most familiar with because like i've contributed to metals and
stuff so i got to see a lot of your code there but i've also like i feel like
looked at a fair amount of your other code that you've done and i feel like your
code is like really practical and easy to understand so like props to you for
that but i haven't hey where are you going no no i'm just it's nice nice to hear
i don't think i've ever heard anyone comment on my videos yeah it's very i
really appreciate your code because i sometimes i have hard times with like
really crazy magical code and not in a mean way but your code's not crazy
magical most of the time i can like understand it so yes there's tons of
mutations and bars and whatever like it's it's not the style of programming in
scala that's advertised in the community that's sort of what i wanted to ask
about because there's actually two things that i've noticed in with your code
that maybe differs a little bit from a lot of the other scholar code that i've
seen is uh one you don't seem to i think i found one actually the lsp4j library
or lsp4s i mean had monics in it that you added i looked at the pr from like
five years ago or something you've done like archaeology i was digging through
github like what commits did this person do in 2015 that i can ask about that's
like the only large pr that i've seen that you've added like an effect library
to a lot of the things you've worked on like is it purposeful that you stay away
from effect libraries uh they're awesome like using monax is like the sweetest
period of programming for me in scotland i love monics it's one of the highest
quality libraries that i i that i'm familiar i have experience with so so alex
has done a phenomenal job with monics i love it we so i never used it before
then then this is when we were starting medals and and the original like when i
was working in my spare time uh it was all moniks and observables uh the whole
project i we ended up implementing where i implemented a clean fresh
implementation of lsp for for s like from the grounds up by json rpc protocol
kind of read write a loop with monox which was like really cool constructing
tasks from the from scratch and whatever um no but i i we ended up removing it
partly because i wanted to reduce the dependency for trend of the metals called
but it's not that it does have a matter metal system running until 12.  yeah but
uh i i feel like i think the biggest there was a lot of with the upgrade it was
going to start on cats i i would see in pill requests people would start pulling
in cats for other unrelated things where i wouldn't have done that and and it's
kind of like the same when we had scala test in in our test phases in our code
base that like suddenly someone would send up here and they'd use a different
style of scala test testing and i feel like that was opening up like the
floodgates for for various different kinds of ways to do things and it would
often be a distraction from just like the goal of shipping the language server
turns out that i don't think that that's the most important decision you like in
the case of these projects i think if i was doing like a proper web server back
end i would probably use an effects today yeah another thing like that i've
noticed and when i first started with metals it was not surprising to me well it
was surprising to me to be housed i don't know why i was hesitant to say it was
surprising to me it was very surprising to me it was a little bit hard to follow
because i just didn't understand it at first because there were some layers of
indirection and there were a bunch of java interfaces and i know that scala fix
has the same thing i think mdoc has the same thing as well uh what is it why are
you using java interfaces everywhere so just for the audience to understand so
like with all scala format with scala fix with mdoc and metals all of these use
the same they all publish a like a package to maven that only contains java
interfaces and the whole purpose of these interfaces is to so like this is the
public api and you can use those interfaces regardless of what scala version
you're in and and with some class loading magic you can keep the class like the
class path isolated from like you can depend on scala format but you don't have
to add scala format to your class path to use it as a library and this is
critical for like if you're doing an spt build plug-in you would like pull in
scala media version four and then you use kalafix and it would it's gonna make a
version three and it would be binary compatible and you would have like an
exception but if you use this interfaces pattern you can isolate the two and
they can run together in the same jvm process so so there's some navigate jvm
details on why this is necessary or wait what happened i think it ends up being
really clean like i would want to do the same pattern if and even if without jvm
quirks aside i would still want to use the same pattern today in a greenfield
project because it's really good to have publicly inter like interfaces for what
you're supporting and they're stable um yeah does that answer your question for
sure yeah it's and i actually i didn't fully appreciate it either until i ran
into that problem when i was working with a like a scala project that was purely
scala and was trying to call a trait basically from a whole bunch of different
versions of scala and i needed to like pull down every version of scala it's
like it was really it was really hairy to to get right and i was like oh like
the java interface is here would make this so much easier right and and this is
i i this was the dotty compiler was where i saw this pattern used for the first
time because they had eight dotty interfaces artifact which had java interfaces
from the we're using the java the compiler scholar three today um so that's
where i saw it and i was like and well i think the svt is really i think maybe
the bigger pioneer but sbt has its own interfaces and large amounts of java
implementations classes for the same reasons spt needs to jump across the
barriers of scala versions yeah i just thought of this question because you're
mentioning spt do you have a preferred build tool that you like to use for i
mean i think it's awesome i i love spt and there's almost nothing i feel like i
can't to do with sbt unless i had a if i was building a company and i was
expecting to have like 100 employees i'd probably use bazel yeah but svt i think
is and it has improved dramatically over the past years uh lord thanks to some
really active uh contributors ethan atkins has maybe ever butchered his name but
uh has done some phenomenal performance work and eugene yokota and tons of other
people as well so it's a big thanks to all of them because spt really has
improved yeah and i i i mill i tried it for a while and it's really cool as well
how is it probably the the uh i've tried to copy his coding style the most
because i i love digging into his libraries and i've learned probably the most
in terms of like how to write scala by reading glee house and libraries and and
mill is just fantastic uh the model and the basal implementations and everything
so like it makes so much sense for me that people enjoy using mail and they
don't like using svg so me saying i like spt does not mean that i like i think
it's just i've got i've gone so deep into svt yeah that like i just i'm not look
i'm not shopping for other build tools at the moment for my small person
projects like uh i have all of my skt plugins and and everything but for someone
starting fresh and they don't have like six years of having building their own
spt plug-in ecosystem and whatnot and i think it's completely reasonable to try
mail or something else you mentioned if you had a huge company of like hundred
people you would want to use bazel why bazel over sbt in that scenario just
reliable caching you don't have to restart and command ctrl shift like controls
like escape kill the services stay in bad cash it's just and i think it's a true
tragedy of modern software engineering like how often something is fixed by like
restarting your computer or or cleaning the cache yeah and we nobody needs to
really er that's fine but uh no but uh yeah i can't see anyways but uh uh
bezel-less um just fry construction is really rigorous with how inputs we need
to produce outputs and new cash you can really rely on the cash basically okay
and and at any certain size in your code base it just beats on performance
basically for everything so you mentioned like a little bit like earlier when
you were working at your scala center days you mentioned source graph now but
you also have that period in between which i'm assuming is twitter where you
probably had some bazel experience did you work much uh at bazel when you were
at twitter so so twitter we i worked at the end i had a really fun project uh
that was twitter is in the process of marketing to beijing from pats and pants
is a monorepo style build tool which is heavily inspired by bazel but it's
written in python and then has it evolved in a different direction over the
years and so i did work with bazel for a couple of months there in the end we
were doing i was working with dependency resolution when you because at twitter
you would end up having like multiple versions of protobuf different teams that
the company would be using different versions of libraries yeah and and a lot of
the off-the-shelf solutions for bazel they kind of assume that you'll have a
single version of product for the entire monitor we like mono repel and i i i
don't think that single version policy is the way to go i think it really holds
you back like it's it wait it makes a lot of sense in maybe a pool's case but i
don't think it makes sense for most other companies yeah so allowing a team to
start using cats three and then while other teams can have six months window to
upgrade later and so we were kind of building a solution like that for bazel and
bazel was able to encode this beautifully and get all of the like benefits of
your proper structural sharing in the caches all right so we we mentioned this a
couple different times so we were like jumping all over the place yeah we were
going all over i'm trying to at least have some type of like line of thought to
like continue to ask questions but i have a ton of questions so like you
mentioned uh scholar flicks and you mentioned mdoc and we haven't talked really
about much of them but like we'll start with maybe with scholar fix because you
mentioned you tried to get some diagnostics from scholar fix into an editor but
what was sort of the impetus of starting scholar fix like where did that come
from like and ironically models today still doesn't have scala fixed diagnostics
yeah i know that's that's what i wanted to comment on i was like this never
happened actually like it got pulled out and and it still hasn't been added
again so uh ironically like i think gabriela still wants to get this feature
[Laughter] but he did get like all the other things uh instead like okay so but
yes you were asking about sorry uh skalafix yeah just like how did you start
scholar fixing where did that come from so that was uh at the time i was i
finished my internship with google i did the scala format project for my
master's thesis and then i was looking for a job and i was i'd say very very
close like super close to accepting just the consulting gig in bergen norway i
had the offer and i i was going to accept it and then i thought maybe i should
give it three more weeks and see whatever comes up instead and and uh because my
wife my partner at the time was was it was doing a phd or my she's my wife now
but uh she she was doing 18 birth in norway so i i wanted to be able to live
there and um then eugene vermaco hooked me up with uh a call with with martin
luther he said like hey you should really try and hire him for this new scholar
center thing that you're starting and and so they were coming up with like what
would be a suitable thing that i would work on if i i joined the center and and
at the time martin was working on dottie and he was this is in 2016 and then he
was like well we want to do some kind of automatic code migrations for dottie
and um so that's how scholarly started basically it was uh fully funded i was
employed i was never i never earned any money whatsoever working on scholar
format that was purely just me as a master's student and free time uh scala fix
i was hired and i was working on that during during for two years probably yeah
okay and it was a hard i'd say just that's probably the most painful hardest
project that i've worked on i think it's come in a good good place today but i'd
say that is it was pretty brutal in terms of like i just couldn't get it i
couldn't figure out how to provide a good api to refactor code the domain is
hard and it still isn't really perfect in my opinion but it i think what it what
it does advertise that it has it it solves those problems well uh so so it'll
migrate our code to scholar three right like we said well there is and and that
not not thanks to me because medium has done a phenomenal job with the scala
free migrations and it turns out that a lot of the work there is not only just
migrating the code it's updating your build tool and like figuring out that like
these versions like the big thing people spend most time with is like figuring
out what version of this package they're going to use between skeletons kind of
three and suddenly being in this hole we're like yeah so so uh the scala center
and medium now they've done a phenomenal job to actually package up like a
complete tool to do scholarly migrations uh i feel like it was too early at the
time like we were looking we were too focused on the end goal was like we won
scholar three migrations and but most of the time was spent like how do we do
how do we do how do we do refactoring yeah on code bases and i think at the time
i was collaborating very closely this was heavily i was working very closely
with you jim burma twitter and and they wanted to do refactorings in their
monorail where they have the twitter code bases organized in a way that like you
can't easily just run on a local computer refactoring rule against the entire
monorepo you need to be able to do it distributed ways and aggregate and whatnot
yeah so so we we kind of threw in some requirements into the mix that that made
it even harder or like yeah so like it wasn't okay to just like edit the source
code while we were compiling it like there were tons of things we couldn't do
yeah so um but uh that collaboration i think made it really good in the end
unfortunately we were given enough time and funding to really get it to a place
where it should be yeah so i mean some of these projects are really like digging
into like the internals of like asts and then rewriting things like some of this
stuff is like pretty pretty in depth like how would you say that you approached
learning a lot of this stuff especially it sounded like you were pretty you were
still in school almost when you were coming out and getting your i was yeah like
yeah is that challenging like how did you how did you learn this stuff was it
was well i studied this i know how to do it or no you don't learn like i didn't
learn i'd say any of this stuff in university um and what you learn in
university is more like other things that are helpful but but not the actual
like stuff that you need like you don't learn how to hack on the compiler or
whatever and the i think i i think it was crushing for me because i've done like
to work on skalafix because i just i'd worked on the formatter which is a super
simple domain it's like you read a string you spit out a string and then you're
done you don't have any need to like hook into the combination step to get type
in phones and whatever yeah and the then you you could have like just done a
compiler plugin and you would have all of the type checking inference and
whatever working out of the box but then you would have been working with the
trees and the syntax model of the compiler which is kind of heavily de-sugared
from what you would have in the source code so we said okay well let's do like
let's start with the perfect source code and then like this is the perfect
syntax model and try and sprinkle the types and the inference but now we had to
like copy this whole semantic model of the compiler into some other place and
and we did that with like a persistence layer you wouldn't emit a bunch of
semantic information to disk and then kind of map that onto these parse trees
that were at high fidelity they would faithfully represent the code the way it's
written in source and this is just i'd say a completely more like i have more
respect like this is a harder domain than a code formatter yeah okay and and
there's still some like fundamental features and scala things that i would love
to get like you can't really get the type of an arbitrary expression like that
would have been trivial to support in the compiler like no-brainer and i also
just like said that yeah but you don't really need it for anything except like
kind of do if you're doing i'd say a niche set of use cases you needed but uh
somebody had a question in chat which i thought was kind of relevant because
you've you've worked you've been able to be in jobs that you've worked a lot in
open source as well and the question was when you're working at product
companies like twitter how did you get time to do open source do you work on it
in the evenings or did you get to work on it during your day job because you've
you've produced a ton of tools so like how do you have time to do all that i
have been fortunate to since pretty much 2016 i've done a ton of work in my
spare time or let's say i'd rather want to just say i've worked more than 40
hours a week because i've there's been a very gray area between like my work and
my hobby or side projects i've been more largely funded or employed for the
projects that i worked on and so i don't know how much advice i can give except
like i think it is like don't put unreasonable expectations on what you will do
if if you have a full-time job and then you're hoping to to build like the way
you do it chris like i don't know how you manage like well now you fortunately i
know you're maybe ready to use some of your work time to do these things uh
because i think it's unrealistic to expect people to have essentially a side job
alongside their main job well because i also have a personal life like uh and
and i i do and really relish the time where i'm not voting as well and so i'd
say the best advice that i ever got on this topic was from eugene vermaco he was
a fantastic mentor to me he said that when i was working on medals in the
evenings and kind of growing this unhealthy side project that would that was
completely i'd say eating up all of my cpu or like my my charged batteries of
mental cycles in the evenings and then i was just tired during the day and i
wouldn't be able to do any work on spa fix which was my main assignment and he
kind of pointed out that like well or he basically just told me like why don't
you just do that thing during your work day like it was an easy question i can't
remember what he said exactly but he was like yeah like just figure out what you
need to do so that you're doing this as like when you wake up in the morning and
like this is what i'm supposed to be doing like why don't you just make it that
and i'm not gonna say that makes sense for almost very like it probably doesn't
make sense for as many people [Music] as an advice i i for some reason i managed
to then hop on and then do another job where i was doing this stuff and then i
helped them and then i'm still doing all of this stuff during my day job and but
uh i don't know maybe i'm just it's it's uh i was i would have easily not been
doing tooling if i hadn't met you know found sourcecraft and got in the
opportunity and like all of the stars and the moons aligned for the job that i
have right now and and i was i'd say it was not at all a certainty that would
have happened yeah and if that wouldn't have been the case i would have been
probably writing uh banking software uh for local norwegian rights okay uh so
and that is what i was expecting to do in november basically so which is quite a
little bit different than what you do now right yeah i was ready to i mean like
i i i've chosen i want to live in norway and there's just no norwegian company
that ever will employ someone to work with sure what i'm doing right now yeah so
uh and i was that was a trade-off i was completely willing to make as well yeah
so i don't know if that's aspiring or helpful advice you just feel more
depressed now because you can't do that no okay but uh it's uh all i can say in
terms of like the niche and the domain is like there's very few organizations
that are employing people like the advice first one i have is like actually
ideally try and get paid while you're doing it and secondly like there's very
very few places that will pay people to work on these things so hopefully that's
not to um sad so realistic if you were given like six months to do whatever you
wanted what would you work on i i hate or like i don't i don't want this to be
the answer but it it's it's a cliche but i literally would be doing exactly the
same thing i'm doing right now which is my problem really that's what you'd
really do wow wow i'm having so much fun right now is amazing so like because i
if i'd go six months and do whatever it's like it'd be a solo thing and i
already have experience for where that goes yeah and like i mentioned you said
like what's the biggest high impact thing that's out there and i i really feel
there's so much untapped potential in cold search and all of the side features
you can support if you really have a back end like a service that is running and
continuously analyzing and providing like on-demand as uh so um and i think it's
so exciting that i i really am enjoying tapping into other ecosystems this week
i was trying to hack on a python indexer and suddenly i have to comment on like
how hard would it be to do a c-sharp indexer and like and we're like how do we
support kotlin because we have customers who want scala java and kotlin like
across navigation all of them and that is a just product that only exists within
jeopardy like intellij doesn't exist anywhere else like some that's like across
jbm language and we have enough for scholar javascript why not all of these
problems i think are super exciting and i would if i had six months i would just
keep on doing that probably yeah well at sourcegraph from what i can tell it
heavily relies on lsif which you mentioned lsp before we mentioned bsp we
mentioned acronyms yeah so maybe like people if people aren't familiar like can
you what is lsif and uh like wow just what is it you may hear the the ice cream
truck is just driving on the street so maybe we should stand for ice cream no no
no but uh ls if so there's too many acronyms like too many i don't think they're
too many but there's many so i understand that it's confusing if someone's
coming to the scene and like you're not expected to understand everything when
you hear people name drop elsif lsp bsp like obviously if you have ever concerns
like ask in getter or whatever so what's lsif that is there's lsp which is the
language server protocol that microsoft authored lsif is also authored by
microsoft and by pretty much the same person from what i can tell you who was is
basically a persisted on disk serializable version of lsp so lsp requires you to
keep compiler processes and everything running and that makes it very and for
like you can't easily deploy it as a service because you don't have language
like metals is not at all designed to be running in a server uh to support like
thousands of concurrent users yeah it just does like it has a single thread
where it keeps the compiler because the compiler is single throughout it so ls
if allows you to kind of persist whatever info to disk you could put into sql
index and then you could do some features uh by that so sourcegraph consumes
lsif and it's able to do find references like across multiple repositories but
and it does that in milliseconds because it's just querying stuff out of the sql
database not like spinning up metals to figure out like sure the definition of
something okay uh so yeah uh i lsif is different from symmetry to be but in our
case so we emit semantic to be and then we have a separate step that just
aggregates a bunch of semantic dbs that produces a single lcd file okay uh and
so if you produce method you basically have an elsif emitter get you get it for
free today if you want to nice okay kind of related to one of the things that
you were talking about about how like uh you really feel like there's so much
untapped potential in code search when another question from chat was what do
you think is missing at the moment in the scholar tooling ecosystem like is
there anything that's really missing i would love uh to so now i have colleagues
or not they're all familiar with the skull ecosystem and i i tell them they
insult ca because that's the way you install everything else it's the equivalent
of npm like you can't do javascript development unless you it's an npm or yarn
and so i would love to have corsier along with all of the other things kind of
in a single interface and there may or may not be some people who may be are
working on something in the disruption but like that would be the most awesome
thing that's missing because if it existed i could tell my colleagues like just
install the scala commandmentally yeah and and it has formatting embedded and
fixing and like the lsp stuff about it and it installs jvm for you and then
there's like some hidden features in corsi i'm pretty sure too because i think
it was actually an issue you said like here that sounds like i'm stalking you on
github but i i i i look through i've like this is a totally total aside but like
i learned so much from reading issues and comments on github of like people's
thought process so i do read a lot of like issues on github just to like see
what like problems people hit on but uh one thing i i saw you even posted one
time and i read it and i was like wait wait a second uh corsia can pull you can
publish with corsi like there's oh yeah no like you can't and ken you don't want
to do it i never got it working uh or if i did get it where he was after several
uh it's i hope that will work at some point but um there hasn't been a product
market fit kind of there for this product put it that way like uh alexander has
has done an incredible amount of work i'm super excited to hear that he's
working with bernie's lab today and um he's done so much work on corsiera and
it's like uh i hope that published feature works but it does not don't use it
it's incomplete it's like it's the same i have a bunch of uh what are they
called easter eggs and some like m-doc has as maybe more features than people
know because like like a language server [Laughter] among other things okay yeah
we didn't even we didn't talk about it but yeah okay yeah i i also like cannot
speak high enough about corsier i think i don't know if there's another i mean
there's a few tools in the scholar ecosystem that i use literally every single
day and uh corsi is one of them like i like i like i especially use it even more
now like i've started to like embed some of the functionality in new of him that
i can quickly do it without having to jump to the terminal so like yeah of
course he is i think it's validating you say that chris because i think like i
use it every day but then again like my job is like i'll do i'm implementing a
dependency resolver for for twitter or like i'm trying to index all of maven for
source code for something so i i don't know if i want to tell other people like
yeah you should drink the kool-aid man like i use it every day like then you
probably need to use it every day as well but i think if you're dealing with
dependency updates or like figuring out like what depends on what is this a fat
dependency all of these things i i use the cli focusing yeah um for sure all
right so i don't want to take up tons of time because i run over like over time
already but i do have a couple of like a handful of other like quicker questions
i want to ask you if you have time for them so alexandre is on the chat i hope i
didn't say anything wrong soon for the published stuff see maybe maybe this was
just the push he needed to get the published stuff across there yeah so we'll
look at it that way but uh what you mentioned it a little bit and i mean i know
this question but you also mentioned you used intellij what editor do you use
and why i use v's code increasingly infrastructure so i i did use intellij i
implemented metals in intellij because metals wasn't good enough and then it was
it ended up actually i ended up switching when it was good enough like it didn't
it wasn't always it was worse than intellij and a lot of friends but there were
several friends where metals was better than intelligent primarily low latency
completions where the completions will actually give you all of the results in
the first completion like intellij has this smart completion where you have to
do double completion and the second one is just too slow like it'll take one two
seconds it's super annoying if you have something like files provider in scope
and then you want to import java nio files and then you do files and it will
only like suggest files provider double tap i say that's the feature that made
me literally want to use this code instead of intellij okay so if someone a
japanese is listening like fix it so i but then uh it's really sad i think that
vs code does it implements lsp in kind of some bad ways like the workspace
symbol where you're searching for symbols in the workspace it just uh it it will
filter out stuff that the language server actually resends like this code
applies its own uh editorial choices on like we think this item doesn't match
the query that the user type so we're going to discard it but models support
stuff like if you want to search for collection immutable try you can just do
like s dot c dot t uh but this vs code will be like well these dots like there's
no dot in in this name here um so and then the vim bindings in vs code feel like
they're regressing all the time i don't know what's what's happening so i feel
like i should try neovim soon because i did use them full-time uh with python
yeah yeah well i hear there's some pretty crazy work going on in the new of him
land yes so i i hear so too so i am increasingly frustrated by a lot of these
things like it's annoying to have implemented features that were working in vs
code two years ago like i spent blood sweat and tears getting some of these in
and then these features are just silently taken away from you i just i just
noticed because they're not working for me and then i realized that huh so like
all of these people using these gold medals like they don't even know this
feature exists like it works in them uh you say that like it's surprising like
it works and then come on blood sweat and tears went into making it work in vim
yes i by the way if you're if you want i i dug up the email that you sent me for
the first time that i wanted to read it i thought it was kind of funny so uh
because you sent me an email in september 2019 and the title is the subject is
medals is awesome can i help and um because i didn't know who you were and it
really i won't read the email uh indeed like exact but i i what i wanted to
highlight was because you said you're a big fan of medals and you're asking like
an insight of what how you could contribute you know and i i said yeah that's
cool uh i replied at like 12 55 something and then 12 58 but three minutes later
i'm standing yeah one idea that has been floated around is one idea that i
slowed around but nobody has worked on yet to my best knowledge is a custom
cloud bin plugin for metals it would be nice advent support for models msp
extension and for example three views and maybe also automate the installation
somehow and this is basically what you've been doing my future right there you
just outlined my next two years it's like it's sun three minutes later and i was
like yeah by the way maybe whim you should check that out you know and then like
two years later you're still doing that i thought that was cool so i'm really i
i maybe i'll be using them uh soon yeah it's it's funny you you talk a little
bit about like the stuff that vs code does and it's also the other way around
too like fiasco sometimes and i just realized this because in neoven metals i
just implemented the tree tree view protocol in lua and i didn't realize so like
in vs code there's also like a tree view panel or like it's not a tree view
panel but it's a panel there and i didn't realize how much extra stuff vs code
does with that panel that we don't have to do like uh for example like if if
something changes and we need to replace an entire node and everything
underneath it it will like cache the state of that tree and uh because all all
metals is doing is saying here's this new node it changed if you want to know
everything underneath it you have to do like 10 calls to get them all and then
somehow keep the state of what was expanded and not and and that took so much
more work than i was assuming it would and then looking at the vs code extension
i realized it did like vs code just does a lot of that with that with trees for
like i think that's partly the reason why it is so successful because it was
super addictive to do a vs code extension it was super fun and partly i think
like this three like it just things just worked and then i didn't have to deal
with it but then um yeah but then i think it results in like it's probably
holding on to a bunch of cash that it doesn't have to like if i knew what i was
doing like i probably could have thrown discarded that flash so um which i feel
like hurts it as well yeah okay and it does a lot of filtering on the
completions where you don't really have like it's super frustrating to have
spent a long time building like proper sorting because like the language server
has context that like this thing should really be at the top except vs code just
you know imposes its own editorial sorting as well so when i i i'd follow the
test passing and then i would just spin up these codes like okay great the work
that i've been doing today is meaningless because vs coding well at least two
people who are using vim and emacs are going to enjoy the nice ordering yeah
it's great so so another kind of like different question is uh what do you think
we can do to make the tooling environments more welcoming to like diverse
individuals and people that are underrepresented in this space and i think we've
talked about this a little bit in the past because tooling at least in well in a
lot of ecosystems especially in scholars seems to be a whole bunch of white
dudes so is there anything that you have in mind that could help you know bring
more underrepresented individuals to feel welcome in this space i think it's a
great question chris uh and um i uh like i just want to echo that i agree with
you i i feel partly responsible because the skelemate organization has had a lot
of like contributors all over the world who are to multiple projects and and i
just looked like i was scrolling through the members of the organization and
like they're they're all white dudes like myself and uh i uh so i'm not gonna
claim any kind of like i don't have the answer for this because clearly what i'm
doing is not what we should be doing so uh the only thing that i know where i
think was interesting or observed so like in twitter it has like three large
organizations there was like the the platform side which where i was part of and
then there's the product side which is building the twitter web app and the ios
and android apps and then there's the revenue side and the platform statistics
were way worse in terms of diversity uh than the other ones as well yeah and and
i don't mean to throw that as an excuse but like there seems to be that uh there
it's not diverse enough in tech in general and it's even worse when you go into
the niche of tooling um so i i don't want to throw any like probably the best
thing we should be looking at is like why are um a lot of people from different
backgrounds not sticking because i think that there's people coming like but
they're not staying i i don't like like complaining about like pipeline problems
or something i don't think that's the case i feel like we can do a better effort
today to attract the people who are actively contributing um so okay um but yeah
i don't know if that's just me rambling about you know something i'm not really
good at yeah no i mean it's not something i expected you to be like well this is
the this is the issue but yeah part of the problem is that often we just don't
talk about these things either i think i'm really glad to bring it up like it's
a topic that i think is super important yeah yeah for sure um there was another
question in chat too that i wanted to bring up uh somebody said that something
they're interested in is learning both rust and scala and they asked how do we
improve scala's error messages russ our next level i'd love to see scala get
there any tips if i'm interested in contributing to this space uh the compiler
error messages you there there's quite a lot of more infrastructure for better
error messages in scala 3 than there are in scala 2. so if you're interested in
contributing to that space would be to identify the errors that are bad today
and and so this was an initial initiative from felix mulder who was working on
the dotted compiler for a year and then and then since then there's other
movements that are updates that i'm not haven't been following too closely but
if you're interested in that space i would go to the scala 3 compiler and really
work on that like i've seen some of the improvements they've done in scala 3 and
they're phenomenal so let's say and just like the implicit not found error
message scala 3 will like show you like i was able to infer all the way up to
here which is a huge upgrade compared to like just like i wasn't able to find
your top level type but i believe so the question like there's tons of
opportunities not just the implicit not found errors but like the basic ones or
just like the parse errors like instead of saying uh found this expected that
like you could probably improve a lot of those tokenization errors where you're
you're pointing like well if you put it in here then you're gonna fix this or
like i was it so and tons of low hanging fruit like low hanging like it's a lot
of work but uh high impact work lying there and it's definitely up for grabs
like go for it i think that would be huge um something i saw recently that i'm
kind of excited about was i saw virtus lab was working on and tweeting about
better stack trace error messages as well like a better way to navigate your
stack traces and a better way to make sense of them and be able to see like
what's actually going wrong or go to the places in the code so yeah that's
really really neat yeah um yeah i i i had to look at a goal panic error this
week and i was like just not trained to the eyes like i wish i just had a jvm
stack trace that would have been fantastic yeah you get used to it for sure uh
well if there are so many other questions i have for you and other tools we
haven't talked yet but at the same time i want to respect your time so i think
i'll just like freeze a little bit and not ask her keep more questions i just
want to thank you again for taking the thank you yeah for for coming uh i think
i speak for hopefully everybody in the chat that like we've greatly appreciated
all the work that you've put into like a ton of tools that people use every
single day stuff that we've talked about stuff that we didn't talk about like we
didn't talk about mdoc hardly at all which i think is that's sad tons of people
so i'm i'm not in a rush to do it okay so then i'm going to ask i don't want to
drag the viewers on like i guess well they just talk they can leave like they
can stay i'm having a good time cool then if it's okay i'm going to ask you some
more questions and if it's not okay secretly send me a message behind the scenes
to be like your questions like let me go okay so uh so let's see don't don't run
chad we got more questions we got i got them i got at least three more tools to
ask about that we haven't even touched it yet so okay mdoc what is mdoc for for
the for the uninitiated mdoc is a tool that reads markdown documentation with
scala code examples and it produces other parallel like markdown files which
contain like will evaluate the scala code examples so it's the equivalent of
treating your markdown documentation as just other programs like other treating
them as programs and you can test them and run them and put them into your whole
kind of build pipeline just as if you would do like a normal unit test suite and
but it makes it a lot of fun to write documentation as well for your scala
libraries because you can interact like get really quick feedback on like how is
this api going to look like on the website uh because you can save and it will
compile and re and so this sounds like well obviously but it's surprisingly it's
lacking in almost other all of the ecosystems from what i can tell a very few
ecosystems have something like this aside and the experience i've had of writing
documentation without mdoc is really brittle i'm like running something in the
terminal and then i copy paste it and then i put it in and then a month later
it's outdated or it's not working it's missing the imports and so that's mdok in
a nutshell and i uh it has tons of other features that are less important
somebody mentioned tut and chat and i also wanted to ask you right because you
have a really good blog post actually about why mdoc was like the successor of
tut uh so if people like haven't seen that before go to the go to the mdac
website i'll post it in chat and and read that blog post but yeah can you talk a
little bit about like uh yeah just about tut and about how mdoc came after that
or like what did you drive from that kind of stuff so so the idea was pretty
much only inspired by thought like this thing already existed in the community
and it was heavily adopted and i was like this is a fantastic idea i should use
it in my projects and then i there were some like i wanted more things out of it
uh a very basic example is like i wanted to be able to put in my the variable
name of my library so i could write like here's how you install this thing and
then i could just put like dollar version and the solution was like you could
use a separate tool like paradox or another plugin that would like rewrite and
put in the version and then you would type it through tuts and your build
pipeline would be like a long sequence of like processors that would be really
marked down spinning you know smartphone and then if you had an error in any of
these pipelines it would be super cryptic because it was like complaining about
something in your generated file if that makes sense so and then when i kind of
wanted to scratch a niche on how to implement it i i i used a lot of the same
techniques that that metals had been using or like um to to it was it's um i'm
doc internally uses a completely different approach than that so like that pipes
all of the examples through the rapple uh whereas mdoc will and and so that's
kind of slow because you have to compile emit byte code and then class load the
bytecode and evaluate it and it's not until you're done with that then you can
go to the next code example and so if you if you have a file that's worked on
file that can easily be several hundreds of lines it can take like 20 seconds to
just go through the whole sequence because you have to go to this whole cycle
for every one example whereas mdoc will take the entire markdown file and
translate it into a single program and evaluate it in a single goal as if it was
just a normal scalar program so like you remove you replace like all of those
cycles with just a single compound evaluate run loop and this dramatically sped
up that like edit test feedback loop which i think is essential if you're
editing documentation and you want to see like does this feel right yeah yeah
and uh am i mistaken and saying that one of those easter eggs in mdoc is that it
can it is also there's also like an entire language server that's that's perfect
i wasn't thinking about that as these were when you mentioned i was like oh yeah
that's another restriction just that small thing yeah so yeah why is that there
and why isn't it used there it's not used because like as soon as you you have
it you want to get called completions inside of so mdot has a language server
that will give you compilers in your editor as you're typing markdown files
which is really nice because suddenly like it'll it'll be spent spinning the
errors and the the workflow i use today is like i have my terminal running and
then my editor but in the editor i don't get any live feedback on like the code
examples uh so the mdoc has its own language server that just does the
diagnostics part but like as soon as you have it you want to get code
completions you won't go to definition or work you want like all of the other
things so it's not advertised or published anywhere because or i think it's
published but it's not advertised because essentially like it felt more natural
to put it into metals although that doesn't work today like that's virtual work
a bunch of new issues and feature requests will be coming in the next week i
heard this had a language server yeah and because i have a like kind of
complicated local setup where i have my terminal my editor and then a live
preview all on the single stream and i don't think like that's something like
you'd have to pair program with someone to understand the flow so i think a lot
of people are like not using the same thing because but uh if you have if metals
had out-of-the-box support for this they would automatically kind of get the
live preview and the markdown as they're editing and it would synchronize
between the editor and the view um we don't even have a feature request for that
i don't think i don't think so no because the market for people who are writing
documentation is so small it really is like it's it's maybe a hundred times more
people who are just writing normal programs than the ones already yeah what are
some of these other easter eggs mdoc has kind of its own script evaluation
engine like it it doesn't really have to evaluate markdown files like it could
evaluate just like regular scala script files as well it's kind of like
amazonite does um and um the you you can in your markdown file actually import
like another scala file or a script file and and i'm not like correctly like you
can also import dependencies from certainly from even uh so in in a way it is a
really really bad slow build tool um because it didn't compile all of this from
scratch all the time uh and and i had some pr's that that never got merged that
would use group and behind the scenes to like do that incrementally there the
whole compile pipeline okay so so then it would be like a script engine that
would be just as fast as like edit tests with your zinc incremental compile all
of that stuff but i realized i was building a build tool and i was like i'm not
going to do that because we don't need another built-in so so kind of related to
that feature i'm going to read this question word for word that i'm going to
break it down so secretly i've wanted mdoc to spawn off a side project that i
can use as an alternative to ammonite to run scripts is this possible you want
to use can you repeat that i want to use mdoc as an alternative to like a
lighter alternative to ammonite that i can just have a script and use mdoc and
run it and execute the script yeah you you could it's i'd estimate it probably
is less than half an hour's worth of work to make it like so it's possible yeah
sure it's possible okay okay is there i'm kind of open concerned about the
floodgates of like other kind of what happens but let's chat about it i i would
be here we already have a question in the chat is there extra steps to enable
this lsp server in mdoc uh yeah um you'll have to figure it out yourself like
that's kind of that it's not it's not advertised not advertised in this i regret
having mentioned it i don't no i i i think it because that was it that also
comes with the vs code extension so you're like you have to set up the language
server and then the vs code extension and a lot of things are tricky to set up
um yeah i don't remember the steps even related to the file imports too that
mdoc can do i even tried to like put examples of this with metals even though i
feel like it's even not uh people aren't aware that you could even do like uh
compiler plugins or scholar c options and stuff as well right in a workshop yeah
and those will work yeah and maybe so just to to explain um largely so mdot was
always this documentation tool and um then in november 2019 that's two months
after you emailed me chris uh i was working on uh worksheets and that was partly
because i wanted to do a proof of concept at twitter to because like the whole
king of the game were like what everyone were trying to figure out was like how
do we support faster iteration speeds for edit for developers and and i was
thinking of like what is the absolutely fastest thing i can do to get someone to
make a change in their online change in their code and then rerun it so i did i
went for worksheets and and so the worksheets and metals are implemented with
mdoc because all of that pipeline of like taking some input compiling it
evaluating it running it showing you the results uh in the same single ui uh was
all implemented in mdot already so it was just a matter of like wiring it into
vs code or metals it's a pet peeve of mine when people say metals in vs code and
they don't say like metals like it can work with other editors it's at the time
there was an untrivial amount of work on the vs code site to do the decorations
so they're like it would have been unfair to say medals and this because then it
didn't work in another editor like and you kind of have to ship a feature with
like an end-to-end implementation for like one editor and that's why worksheets
has completely changed the way that i do reple-ish style things like yeah i know
a lot of people it's like some people are on the rebel side and some people are
maybe on the worship side but like i i don't even i honestly don't even remember
the last time i just opened a rebel to evaluate something like i'm so much more
quickly for me to just like open up a worksheet and boom like i i think it's
amazing worksheets are too fiddly sometimes for me like like probably my fault
but like they do tend to like sometimes stop evaluating or something you have to
reload restart but uh uh i've too i'm also just too used to the print in the
console workflow that i tried to like implement the sudoku software ones with
worksheets and just didn't feel right so uh but i do feel that for certain
things worksheets are amazing um but i i hear from people that use them for
everything and i'm like cool like i i wouldn't implement an http server with
worksheets but yeah yeah it's great anything else about mdoc that that you want
to mention yeah there is one thing that i've like i've wanted for so long which
is uh because we have pretty much all of the information about your code base
your class files your byte like that we could an mdoc could it has this modifier
api you can implement your own modifiers so essentially if you want to do if you
want these code fences in markdown to have a different behavior you can
implement that by like hooking into a plugin api which is implement like yeah
and and what i would love to do is to allow you to fetch the documentation for
certain symbols in your code base or if you want to do like a table of contents
a reference guide like a reference like these are all of the methods in this
class so kind of like scalable but like instead of having a tool that generates
galadop for your entire project you would like you create a markdown file and
you say like let's talk about this class and then you could have like a slim out
view outline of the methods and then you would go and elaborate on each of the
methods and mdot would give you the api so like it would pull in the dock string
alongside so you didn't have to repeat that or it would show you the code where
it's defined like but you you can kind of kind of like notebook style or what's
it called literate program where like where yeah you could you could call these
custom modifiers that would pull in like more api docs about your code base okay
i i that's not implemented but it's like totally right for it like all the
components are there and i feel like it would really be a new way to do because
like a properly documented project contains tutorials reference guides and like
mdoc is mostly used now for like the guides and the tutorials but not the
reference but i'd love to get it to do the references we also didn't even
mention docusaurus like the docusaurus integration i don't think right no which
is a whole other part of mdoc yeah but it's it's the least interesting one it's
like just a super thin layer over the docker source site generator which i would
love to get rid of honestly because like i think it would be nice to have a jvm
implementation and it's just worked well enough that i haven't bothered like the
biggest pet peeve of mine is that the search is not offline so like it's using a
third-party vc funded company i guess to to support like i would love to have
there's no reason why you can't emit like generate the index and have proper
javascript search like yeah probably a vfm project improvement yeah hint hint
chat this is like that that's the other reason for tooling talks is just to get
people in chat to be like that's a great idea i wanna i wanna do that yeah so if
someone wants to do like index like searching like there are some javascript
projects that do this but like i wouldn't mind the scala yes simply like that
would be even better we could we could then provide search on the api level oh
like yeah cool stuff cool and virtuslab is by the way doing huge like tons of
super exciting work with scala doc rethinking scala doc a lot of these ideas are
are in progress or like different ones like they really are driving that front
so i'm excited to see what comes out of that yeah let's jump into a couple of
newer projects that you've been working on too as well new newer uh m units
people ask oh yeah yeah [Music] i saw somebody tweet the other day and they were
like somebody so-and-so asked me why i'm using mdoc what does it have uh and i
think they said something like that's the thing it has like way less and that's
why i'm using it like it's i don't know that sounds terrible probably but it was
a funny tweet i laughed about it and it made me think of you and i was like i'm
gonna ask like olafur what are the killer features of m unit like why did you
start m unit and why should somebody use m unit m unit was largely i like i i
had resisted all tentations to do a testing framework for a long time because i
i was using scala test for a period and i meant like scala format i don't think
it's using it no so but it was using and then i was using um microtest from
hawaii which is fantastic so many good ideas there and but i was starting to
copy paste like a larger larger chunk of helper methods on top of micro tests
and then i was hitting on on as well like the style that i like or the way i
want to organize my tests like the way you like if you just want to run a single
test uh it will you you have to hard type that out you can't use the tab
completion in sbt test only as an example like i don't think you wouldn't notice
that if you're using mail but in spt like the top completion you have to do the
proper testing framework api implementation so there were quirks like that but
none of them mattered enough uh i think so then i can't remember exactly why i
mean it's all yeah and then at twitter i was doing um this bloop integration for
for pants so people could use glue and metals and bsp with intellij and well it
was mostly to get the intelligent psp integration working that's what was
funding the project basically and um i to so so twitter was using a bespoke
implementation of a testing runner for paths which would automatically infer the
scala test j-unit runner annotation so it would run it would use j-unit for
everything but it would if a test class that was scala test was didn't have an
annotation the pants test runner would like automatically assume that it should
be run with a junit scala test runner so i was digging into these things it's a
long story i didn't want to go too deep in it but like that's how it happened
where i saw like that's pretty cool so like if you do junit runners which is
just like a super basic feature of junit you kind of get out of the box like a
complete testing framework so i i implemented the testing for like i i adopted
the junit interface for spt i forked it and then i added the support for the
pants test runner and then i saw like this this is really the spt testing
framework kind of comes with like building blocks for you to build your own
testing library and but if you build on top of junit like you have even more
testing libraries i was like wow so like 99 of the work is done probably maybe
not 99 but like so much scaffolding is there and and then i i started toying
with the idea of like maybe we should build a testing library that we could use
for like scalability and units california and it's two years now later i think
all of the projects have migrated immunity and the the actual api largely was
designed in during a day while i was waiting for my flight from houston to to
london and and i think gabrielle owes huge amounts of credit for like adding
this kind of threes apart because a lot of the interest in them unit actually
came from uh the scala 3 support that was there and an m unit was always able to
support all of the milestone releases for body uh and and that's like would not
have happened at all if cabrillo hadn't like pulled up his sleeves and done that
yeah so cool all right and the last project i wanted to to talk very we need to
go so deep into it i haven't thought about that story in a while wait a minute
how does it have scala js supported if it leverages junit that's a good question
so uh there's tons of copy-pasted code to implement like to get the scholar gs
like the skull implementation of junit actually which is largely copy pasted
from scholar yes because color yes has it as well so like i feel like such a an
imposter really because i was just copying like like that's a good idea it
really is like uh a stew of different ideas okay because color js has junit
support so it has implementations i ended up tearing out like using only small
parts of it but that's how it was kind of just support cool so the last project
i want to ask you about which is one that i feel like a lot of people probably
don't know about because i don't think you've ever talked about it so this is
you know what i'm going to ask already about the biggest issue i'm so excited
about it i've been wanting to get it out for like forever it's probably the
project i worked on the longest without like ever talking about it it's a
project that's public on github you could clone the repo and run the test today
uh but uh don't expect much um it's been used like it's used i'm using it for my
right now also java the command line tool is written in notepad so it's a
command line parser um not much else like to it except that it has like um
because i haven't really refined the pitch for a moment but it's it really is
i'd say kind of like a framework for do shipping a common line tool and it
includes so many things that i've i've had to redo in all of my projects that
have combat online tools so like you get out of the box like how to install tab
completions and type of conclusions that just work if they actually call back
into the comma light tool so like you have a way to implement a method where you
implement your own custom tab completions that's one feature another one is like
the way you test them it comes with a test kit so you can uh have full control
of how you um the output and input and all of that the working directory will
generate like so like a lot of tools are so side effects so like you want to
install something in the user system settings like all of that stuff is mocked
out so that you get actual temporary directories for the entire system of the
computer so you can write really robust tests and and it would like it has an
api to shallow processes that use oslip from hawaii i love how his libraries for
a lot of um but a really cool feature is that you can easily mock an
implementation of a command line tool so like i had the way i test the tab
completion like installation i can mock out z shell um and have it return
something and because often what you do is like the tests will work on my mac os
computer but they fail on linux moped has like the framework that makes this
super easy to like make it past on ci for all of them and um so it it has some
like a super boilerplate free apis to you write a case class basically that's
like and then you write a method that just implements the wrong method and and
with several commands so you can kind of easily reuse often your cli tool ends
up being like this command is kind of like we'll do these three other commands
and the way you would implement that is kind of just by using those subcommands
as fields in your other like it's yeah but i haven't yeah it's without going too
much into detail it's something that it's super ambitious that that's probably
why it hasn't probably been advertised because it's it's too ambitious and it's
not polished so but it does have fancy things like progress bars oh yeah it has
apis for like really nice progress bars and i've done progress bar so often
incorrectly they're super easy to get wrong but the implement i don't know if
it's right now but it feels like the right model it's kind of like it just has a
render loop like every 15 milliseconds or whatever you want it will re-render
the world and you just provide the render step and and whatever concurrent stuff
you're doing will like populate because it's super easy to actually mess up and
and that you're you're render like you're rendering the progress bar too often
so it's your consuming cpu which makes the process slower so yeah small stuff
like that so um i used smokepad at for the project for twitter and then i was
using it for my work now so it is serving a purpose like yeah but maybe one day
it'll be out there um hopefully i get to run today when i i talk about this
stuff so i'm sorry it's okay hopefully after today we'll have like issues and
feature requests on like the the language server of mdoc you'll have a bunch of
stuff on moped and you can thank me for all this renewed interest this is made
more work for you basically but uh i do want to for real this time thank you so
much for graciously giving your time i originally reached out to elephant said
hey we'll talk for like an hour and it's been like two hours now so yeah well
thank you so much for your time i really appreciate it i want to thank you again
for just all the work you do for the community i want to thank you for being
super helpful to me when i first started out and answering my email within five
minutes with with work to do and uh yeah actually like one more part of that
story is that you didn't mention is he he was also willing to like jump on a
call with me and like explain stuff to me which i think like blew my mind
because i was like how is he he doesn't even know me like why are you willing to
jump on a call with me and explain this so thank you for doing that because i
know everybody probably wouldn't have wanted like i don't know just not
everybody would do that so thank you appreciate it very much thank you for
organizing this yeah awesome uh one other thing i wanted to do before we move
too much further is also uh introduce the person that i'm going to be speaking
with next month so uh to shift gears a little bit into sort of just like general
tooling talk stuff uh is like as i mentioned earlier like i really really want
to walk uh talk to various people in the community that i think are doing really
great work uh and i want to spotlight some of the stuff they're working on but
also get a picture of you know why they do what they do how they approach
problems and all sorts of stuff um so i've been reaching out to various people
in the community that i think really fit that and that i yeah just i really
admire the work they're doing so i'm really really happy to say that next month
the person i'll be interviewing is miriam lakar who is working at the scala
center right now she's doing tons of work on migration from scala 2 to scholar
3.  there's some tools there we'll talk about she is one of the people that is
leading the development on scala fix as well uh so i'm really really happy to to
welcome her to have here uh her here that next month i even have somebody lined
up for the month after that which i won't mention for another month uh but if
you do have ideas about people you want me to chat with or that you think would
be cool to hear from please do reach out to me uh and finally i just want to
thank everybody as well on the stream for just yeah thanks for coming to hang
out this is something that i've really wanted to do for a long time i plan on
doing it every month for the foreseeable future until i run out of people maybe
to talk to or or something else happens uh thanks for bearing with us in the
beginning when things were a little bit choppy and we're kind of figuring this
stuff out so yeah i had a lot of fun doing this i hope oliver you also had a lot
of fun doing this and um yeah thank you again so much and i will see everybody
hopefully next month when you join for episode two so have a good night
everybody and thanks so much for coming you
