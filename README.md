# creatureEvolution
Aaron's Indy Sci project studying simulated creatures evolving.
Using Java through BlueJ. (Google Sheets for analysis, JavaFX for visuals.)

## References
* [Markdown Reference](https://guides.github.com/features/mastering-markdown/)
* [Google Talk on PolyWorld](https://www.youtube.com/watch?v=_m97_kL4ox0)

## Goals / Questions
How does changing the environment change which traits are favorable?
  Changing the food spread may have an effect on the final radius of creatures, for example. Maybe it will affect their motion as well.
  How might changing the total food quantity (over time?) affect creatures' desired traits?
How would introduction predators affect the simulation? Can we reach a multi-species (dynamic?) equilibrium? 

## Ideas
* [Greenfoot](http://www.greenfoot.org) Java critters on a grid
   * (Pros: get a grid for free)
   * [Has article about TDD in Greenfoot](https://greenroom.greenfoot.org/files/740/original.pdf)
   * (Cons: Do we want a grid? Or at least 3D space?)
* [Have Java output charts](https://www.fromdev.com/2012/09/Free-Open-Source-Java-Charting-Library.html)
* give ctreatures line-of-sight vision
* give creatures a nose
* figure out how to make a jar from javaFX project


## Currently Doing
- [ ] designing tests
- [ ] writing code
- [ ] designing a brain

## Links
Initial Presentation: https://docs.google.com/presentation/d/1MEo1e8gh9ZiSAboB1UeH3udvDIuOc1QRS0XNVWoFHxo/edit#slide=id.g6e4c618d35_1_7

## Needs to be Done
- [ ] Try profiling to see what's taking time.
- [ ] Possibly add a flashlight to turtes' eyes.
- [ ] Add global debugging toggle.

## Already Finished
- [x] Aaron needs to fill out a project sheet for Michelle.
- [x] choosing a language

## Bug List
- JavaFX sometimes stops displaying simulation when frame rate is too fast

## Areas of Further Study
- Would more realistic DNA expression (dominance and recessive etc. vs. averages, multiple factors) help cooler things evolve?
- Brains ... Language ... Culture?


## Think About
- https://softwareengineering.stackexchange.com/questions/147480/should-one-check-for-null-if-he-does-not-expect-null

## Notes

Because the experiment starts again when either

     1) There is only a single creature left (which cannot mutate further)
     
     or 2) There are over a hundred creatures (which will slow down the simulation dramatically)
     
the results of the experiment are inherently biased.

This may be a problem, or maybe not.
