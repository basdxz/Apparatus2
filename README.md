# Apparatus 2

## Purpose
Apparatus 2 is designed to address the limitations of Minecraft's block/item ID system while streamlining the definition of items, blocks, and tile entities.
By consolidating these elements into a single class, the mod simplifies development and improves flexibility.

## Motivation
The inspiration behind Apparatus 2 stemmed from the shortcomings of existing implementations, such as GregTech 5u and Applied Energistics 2.
These mods struggled with complex and highly coupled design flaws, which made them difficult to work with.

## Approach
Apparatus 2 separates 'ParaTiles/ParaBlocks/ParaItems' by domains, with each mod having its dedicated domain. Allowing for a more modular structure.
The strategy pattern is employed to add functionality, such as NBT data handling, ticking, and player interaction, providing a scalable and maintainable solution.
Reflection-based discovery and loading techniques ensure compatibility across different mods, and custom rendering support can be added for each entity as needed.

## Current State & Abandonment
Unfortunately, Apparatus 2 has become obsolete due to the introduction of [EndlessIDs](https://github.com/FalsePattern/EndlessIDs), which significantly increased the ID limit.
This development, along with performance issues and the accumulation of technical debt, has led to the project's abandonment.

## Future
Despite the challenges faced by Apparatus 2, there is still potential for a future version, Apparatus 3.
This iteration would focus on complete decoupling from Minecraft, providing even greater flexibility for developers.
Avoiding data-driven approaches in favor of procedural/object-oriented implementations would streamline the development process.
Additionally, the scope of Apparatus 3 could be expanded to encompass a broader range of entities, including mobs, particles, fluids, and consumables, opening up new possibilities for mod creators.
