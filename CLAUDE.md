# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Purpose

This is a personal DSA interview-prep notes repository. The single file `sde-sheet.md` tracks progress through Striver's SDE Sheet — a curated list of ~160 DSA problems organised by topic.

## Note Format

Each problem entry follows this pattern:
```
- [ ] Problem Name → (empty, to be filled in)
- [x] Problem Name → concise intuition/key insight, not a full solution
```

The `→` content is meant to be a trigger for recall — a compact reminder of the non-obvious insight or the exact technique that makes the solution work. Notes should be terse: one to three sentences max, focusing on the invariant, the trick, or the edge case that matters.

## Editing Guidelines

When adding or updating notes:
- Keep intuitions short and specific — mention variable names, loop invariants, or pointer positions where they clarify the approach
- Prefer naming the algorithmic pattern (e.g., "Floyd's cycle detection", "Boyer-Moore voting", "Dutch National Flag") before explaining the mechanics
- Mark `- [ ]` as `- [x]` only when a note has been written after `→`
- Preserve the existing section and subsection structure (`### Part I`, `### Part II`, etc.)
- After marking any problem as done, update the corresponding `Done` count for that topic in `README.md`
- After adding a note, also add a Java solution file in the corresponding section folder (e.g. `binary-trees/`). File name format: `{number}-{kebab-case-problem-name}.java`. Put the note as a block comment at the top of the file, followed by a working Java solution. Class name should be PascalCase derived from the filename. No package declarations.
